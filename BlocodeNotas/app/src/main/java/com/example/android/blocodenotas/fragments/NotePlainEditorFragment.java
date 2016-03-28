package com.example.android.blocodenotas.fragments;

/**
 * Created by Adauto on 25/03/2016.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.blocodenotas.R;
import com.example.android.blocodenotas.activities.MainActivity;
import com.example.android.blocodenotas.data.NoteManager;
import com.example.android.blocodenotas.models.Note;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotePlainEditorFragment extends Fragment {

    private View mRootView;
    private EditText mTitleEditText;
    private EditText mContentEditText;
    private EditText mTagsEditText;
    private Note mCurrentNote = null;

    private void makeToast(String mensagem){
        Toast.makeText(getActivity(),mensagem,Toast.LENGTH_SHORT).show();
    }

    private void populateFields(){
        mTitleEditText.setText(mCurrentNote.getTitle());
        mContentEditText.setText(mCurrentNote.getContent());
        mTagsEditText.setText(mCurrentNote.getTagsAsString());
    }

    public void promptForDelete(){
        final String titleOfNoteTobeDeleted = mCurrentNote.getTitle();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Deletar " + titleOfNoteTobeDeleted + " ?");
        alertDialog.setMessage("Tem certeza que quer deletar a nota " + titleOfNoteTobeDeleted + "?");
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NoteManager.newInstance(getActivity()).delete(mCurrentNote);
                makeToast(titleOfNoteTobeDeleted + "deletada");
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static NotePlainEditorFragment newInstance(long id){
        NotePlainEditorFragment fragment = new NotePlainEditorFragment();

        if (id > 0){
            Bundle bundle = new Bundle();
            bundle.putLong("id", id);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    public NotePlainEditorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getCurrentNote();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mCurrentNote != null)
            populateFields();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_note_edit_plain, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_delete:
                //delete note
                if(mCurrentNote != null){
                    promptForDelete();
                }else{
                    makeToast("Não é possível deletar nota que não foi salva");
                }
                break;
            case R.id.action_save:
                //save note
                if(saveNote()){
                    makeToast(mCurrentNote != null ? "Nota atualizada":"Nota salva");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean saveNote(){
        String title = mTitleEditText.getText().toString();
        if (TextUtils.isEmpty(title)){
            mTitleEditText.setError("Title is required");
            return false;
        }

        String content = mContentEditText.getText().toString();
        if (TextUtils.isEmpty(content)){
            mContentEditText.setError("Content is required");
            return false;
        }

        String tags = mContentEditText.getText().toString();

        if(mCurrentNote != null){
            mCurrentNote.setContent(content);
            mCurrentNote.setTitle(title);
            mCurrentNote.setTags(tags);
            NoteManager.newInstance(getActivity()).update(mCurrentNote);
        }else {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            note.setTags(tags);
            NoteManager.newInstance(getActivity()).create(note);
        }
        return true;
    }

    private void getCurrentNote(){
        Bundle args = getArguments();
        if (args != null && args.containsKey("id")){
            long id = args.getLong("id", 0);
            if (id > 0){
                mCurrentNote = NoteManager.newInstance(getActivity()).getNote(id);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_note_plain_editor,container, false);
        mTitleEditText = (EditText)mRootView.findViewById(R.id.edit_text_title);
        mContentEditText = (EditText)mRootView.findViewById(R.id.edit_text_note);
        mTagsEditText = (EditText)mRootView.findViewById(R.id.edit_text_tags);

        return inflater.inflate(R.layout.fragment_note_plain_editor, container, false);
    }


}