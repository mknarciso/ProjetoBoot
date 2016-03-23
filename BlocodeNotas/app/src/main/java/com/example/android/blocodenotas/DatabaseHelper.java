package com.example.android.blocodenotas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adauto on 23/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes_app.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_NOTE = "create table note"
            + "("
            + "_id" + "integer primary key autoincrement,"
            + "title" + "text not null,"
            + "content" + "text not null,"
            + "tags" + "text not null,"
            + "modified_time" + "integer not null,"
            + "created_time" + "integer not null" + ")";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_NOTE);
    }
}
