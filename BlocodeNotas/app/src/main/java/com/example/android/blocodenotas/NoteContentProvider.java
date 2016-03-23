package com.example.android.blocodenotas;

import android.content.ContentProvider;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Adauto on 23/03/2016.
 */
public class NoteContentProvider extends ContentProvider {
    @Override
    public boolean onCreate(){
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String [] selectionArgs, String sortOrder ){
        return null;
    }

    @Override
    public String getType( Uri uri){
        return null;
    }

    @Override
    public

}
