package com.example.android.blocodenotas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.android.blocodenotas.models.Note;
import com.example.android.blocodenotas.models.Rel;
import com.example.android.blocodenotas.models.Tag;
import com.example.android.blocodenotas.utility.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adauto on 27/03/2016.
 */
public class NoteManager {
    private Context mContext;
    private static NoteManager sNoteManagerInstance = null;

    public static NoteManager newInstance(Context context) {
        if (sNoteManagerInstance == null) {
            sNoteManagerInstance = new NoteManager(context.getApplicationContext());
        }

        return sNoteManagerInstance;
    }

    private NoteManager(Context context){
        this.mContext = context.getApplicationContext();
    }

    public long create(Note note){
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_TITLE,note.getTitle());
        values.put(Constants.COLUMN_CONTENT, note.getContent());
        //values.put(Constants.COLUMN_TAGS,note.getTags());
        values.put(Constants.COLUMN_CREATED_TIME,System.currentTimeMillis());
        values.put(Constants.COLUMN_MODIFIED_TIME,System.currentTimeMillis());
        Uri result = mContext.getContentResolver().insert(NoteContentProvider.CONTENT_URI_NOTE,values);
        long id = Long.parseLong(result.getLastPathSegment());
        return id;
    }

    public List<Note> getAllNotes(){
        List<Note> notes = new ArrayList<Note>();
        Cursor cursor = mContext.getContentResolver().query(NoteContentProvider.CONTENT_URI_NOTE, Constants.COLUMNS, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                notes.add((Note.getNotefromCursor(cursor)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return notes;
    }

    public Note getNote(Long id){
        Note note;
        Cursor cursor = mContext.getContentResolver().query(NoteContentProvider.CONTENT_URI_NOTE,
                Constants.COLUMNS, Constants.COLUMN_ID + "=" +id,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            note = Note.getNotefromCursor(cursor);
            return note;
        }
        return null;
    }

    public void update(Note note){
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_TITLE,note.getTitle());
        values.put(Constants.COLUMN_CONTENT, note.getContent());
        //values.put(Constants.COLUMN_TAGS,note.getTags());
        values.put(Constants.COLUMN_CREATED_TIME,System.currentTimeMillis());
        values.put(Constants.COLUMN_MODIFIED_TIME,System.currentTimeMillis());
        mContext.getContentResolver().update(NoteContentProvider.CONTENT_URI_NOTE,
                values, Constants.COLUMN_ID + "=" + note.getId(), null);
    }

    public void delete(Note note){
        mContext.getContentResolver().delete(NoteContentProvider.CONTENT_URI_NOTE, Constants.COLUMN_ID + "=" + note.getId(), null);
    }

    public void generate_PDF(){


    }


}
