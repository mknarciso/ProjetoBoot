package com.example.android.blocodenotas.models;

import android.database.Cursor;

import com.example.android.blocodenotas.utility.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Created by Adauto on 25/03/2016.
 */
public class Note {
    private Long id;
    private String title;
    private String content;
    private String tags;
    private Calendar dateCreated;
    private Calendar dateModified;

    public String getReadableModifiedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy - hh:mm ", Locale.getDefault());
        sdf.setTimeZone(getDataModified().getTimeZone());
        Date modifiedDate = getDataModified().getTime();
        String displayDate = sdf.format(modifiedDate);
        return displayDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public void setTags(String tag) {
        Long noteID = this.id;
        this.tags=tag;
    }

    public String getTagsAsString() {
        return tags;
    }

    public String getTags() {
        return tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDataModified() {
        return dateModified;
    }

    public void setDataModified(Calendar dataModified) {
        this.dateModified = dataModified;
    }

    public static Note getNotefromCursor(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getLong(cursor.getColumnIndex(Constants.COLUMN_ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_TITLE)));
        note.setContent(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CONTENT)));

        //get Calendar instance
        Calendar calendar = GregorianCalendar.getInstance();

        //set the calendar time to date created
        calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(Constants.COLUMN_CREATED_TIME)));
        note.setDateCreated(calendar);

        //set the calendar time to date modified
        calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(Constants.COLUMN_MODIFIED_TIME)));
        note.setDataModified(calendar);
        return note;
    }

}