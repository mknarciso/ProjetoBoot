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
import android.util.Log;
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
import com.example.android.blocodenotas.data.RelManager;
import com.example.android.blocodenotas.data.TagManager;
import com.example.android.blocodenotas.models.Note;
import com.example.android.blocodenotas.models.Rel;
import com.example.android.blocodenotas.models.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotePlainEditorFragment extends Fragment {

    private View mRootView;
    private EditText mTitleEditText;
    private EditText mContentEditText;
    private EditText mTagsEditText;
    private Note mCurrentNote = null;
    private List<Tag> mCurrentNoteTags;
    private String TagsList;
    private Tag tag;

    private void makeToast(String mensagem){
        Toast.makeText(getActivity(),mensagem,Toast.LENGTH_SHORT).show();
    }

    private void populateFields(){
        mTitleEditText.setText(mCurrentNote.getTitle());
        mContentEditText.setText(mCurrentNote.getContent());
        ///zzz
        mTagsEditText.setText(TagsList);
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
                makeToast(titleOfNoteTobeDeleted + " deletada");
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
                    makeToast("Não é possível deletar uma nota que não foi salva");
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

    public List<String> breakTags(String fullTags){
        List<String> result = new ArrayList<>();
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < fullTags.length(); i++) {
            if (fullTags.charAt(i) == ',' | fullTags.charAt(i) == ';' | fullTags.charAt(i) == '.') {
                result.add(s.toString());
                s = new StringBuilder("");
            } else {
                s.append(fullTags.charAt(i));
            }

        }
        if(s.toString()!="")
            result.add(s.toString());
        return result;
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

        String tags = mTagsEditText.getText().toString();
        if (TextUtils.isEmpty(tags)){
            mTagsEditText.setError("Tags are required");
            return false;
        }
        List<String> brokeTags = breakTags(tags);
        System.out.println("DEBUG: " + brokeTags.toString());

        if(mCurrentNote != null){
            mCurrentNote.setContent(content);
            mCurrentNote.setTitle(title);
            NoteManager.newInstance(getActivity()).update(mCurrentNote);
            TagManager.newInstance(getActivity()).addTags(brokeTags, mCurrentNote.getId());
        }else {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            Long note_id = NoteManager.newInstance(getActivity()).create(note);
            // ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ
            TagManager.newInstance(getActivity()).addTags(breakTags(tags), note_id);
        }
        System.out.println("TAGS" + TagManager.newInstance(getActivity()).getAllTagsString());
        return true;
    }

    private void getCurrentNote(){
        Bundle args = getArguments();
        if (args != null && args.containsKey("id")){
            long id = args.getLong("id", 0);
            if (id > 0){
                mCurrentNote = NoteManager.newInstance(getActivity()).getNote(id);
                //TagsList = TagManager.newInstance(getActivity()).getAllTagsString();
                TagsList = mCurrentNote.getNoteTags(getActivity());
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
        return mRootView;
    }


}