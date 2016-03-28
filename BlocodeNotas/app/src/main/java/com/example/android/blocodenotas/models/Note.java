package com.example.android.blocodenotas.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by Adauto on 25/03/2016.
 */
public class Note {
    private Long id;
    private String title;
    private String content;
    private List<String> tags = new ArrayList<String>();
    private Calendar dateCreated;
    private Calendar dateModified;

    public String getReadableModifiedDate(){
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

    public void setTags(List <String> tagArray){
        for(int i=0; i<tagArray.size();i++)
            this.tags.add(tagArray.get(i));
    }

    public String getTagsAsString(){
        String all_tags = new String();
        for (int i = 0 ; i < tags.size(); i++)
            all_tags = all_tags + " #" + tags.get(i);
        return all_tags;
    }

    public List<String> getTags(){
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

}

