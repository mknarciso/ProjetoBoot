package com.example.android.blocodenotas.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.blocodenotas.utility.Constants;

/**
 * Created by Adauto on 23/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "blocodenotas.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion ) {
        db.execSQL(CREATE_TABLE_NOTE);
    }

    private static final String CREATE_TABLE_NOTE = "create table "
            + Constants.NOTES_TABLE
            + "("
            + Constants.COLUMN_ID + "integer primary key autoincrement,"
            + Constants.COLUMN_TITLE + "text not null,"
            + Constants.COLUMN_CONTENT+ "text not null,"
            + Constants.COLUMN_TAGS + "text not null,"
            + Constants.COLUMN_MODIFIED_TIME + "integer not null,"
            + Constants.COLUMN_CREATED_TIME + "integer not null" + ")";
}
