package com.example.android.blocodenotas.data;

import android.content.ContentProvider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.example.android.blocodenotas.utility.Constants;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Adauto on 23/03/2016.
 */
public class NoteContentProvider extends ContentProvider {
    private DatabaseHelper dbHelper;
    private static final String BASE_PATH_NOTE = "notes";
    private static final in NOTE = 100;
    private static final int NOTES = 101;
    private static final String AUTHORITY = "com.example.blocodenotas.data.provider"
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY +"/" + BASE_PATH_NOTE)
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        static {
            URI_MATCHER.addURI(AUTHORITY, BASE_PATH_NOTE,NOTES);
            URI_MATCHER.addURI(AUTHORITY,BASE_PATH_NOTE + "/#",NOTE );
        }

    @Override
    public boolean onCreate(){
        dbHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String [] selectionArgs, String sortOrder ){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        checkColumns(projection);

        int type = URI_MATCHER.match(uri);
        switch (type){
            case NOTE:
                break;
            case NOTES:
                queryBuilder.appendWhere(Constants.COLUMN_ID + "=" + uri.getLastPathSegment());
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri)
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db,projection,selection,selectionArgs,null, null, sortOrder)/
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public String getType( Uri uri){
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private void checkColumns(String[] projection) {
        if (projection != null) {
            HashSet<String> request = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> available = new HashSet<String>(Arrays.asList(Constants.COLUMNS));
            if (!available.containsAll(request)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }

}
