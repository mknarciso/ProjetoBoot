package com.example.android.blocodenotas.models;

import android.database.Cursor;

import com.example.android.blocodenotas.utility.Constag;

//import java.lang.reflect.Array;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

/**
 * Created by Murilo on 29-Mar-16.
 */
public class Tag {
    private Long id;
    private String tag;
    //private int note_id;

    public void setId(Long tag_id){
        this.id = tag_id;
    }
    public Long getId(){
        return this.id;
    }
    public void setTag(String inTag){
        this.tag = inTag;
    }
    public String getTag(){
        return this.tag;
    }

    public static Tag getTagFromCursor(Cursor cursor) {
        Tag tag = new Tag();
        tag.setId(cursor.getLong(cursor.getColumnIndex(Constag.COLUMN_ID)));
        tag.setTag(cursor.getString(cursor.getColumnIndex(Constag.COLUMN_NAME)));
        return tag;
    }


}
