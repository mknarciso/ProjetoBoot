package com.example.android.blocodenotas.models;

import java.lang.reflect.Array;

/**
 * Created by Murilo on 29-Mar-16.
 */
public class Tag {
    private int id;
    private String tag;
    private int note_id;

    public void setId(int tag_id){
        this.id = tag_id;
    }
    public int getId(){
        return this.id;
    }
    public void setTag(String inTag){
        this.tag = inTag;
    }
    public String getTag(){
        return this.tag;
    }
    public void setNoteId(int note_id){
        this.note_id = note_id;
    }
    public int getNoteId(){
        return this.note_id;
    }
}
