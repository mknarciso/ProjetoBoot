package com.example.android.blocodenotas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.android.blocodenotas.models.Tag;

import com.example.android.blocodenotas.utility.Constag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murilo on 29-Mar-16.
 */
public class TagManager {

    private Context mContext;
    private static TagManager sTagManagerInstance = null;

    public static TagManager newInstance(Context context) {
        if (sTagManagerInstance == null) {
            sTagManagerInstance = new TagManager(context.getApplicationContext());
        }

        return sTagManagerInstance;
    }

    private TagManager(Context context){
        this.mContext = context.getApplicationContext();
    }

    public long create(String name){
        ContentValues values = new ContentValues();
        values.put(Constag.COLUMN_NAME,name);
        //zzz
        Uri result = mContext.getContentResolver().insert(TagContentProvider.CONTENT_URI, values);
        return Long.parseLong(result.getLastPathSegment());
    }

    public List<Tag> getAllTags(){
        List<Tag> tags = new ArrayList<Tag>();
        //zzz
        Cursor cursor = mContext.getContentResolver().query(NoteContentProvider.CONTENT_URI_TAG, Constag.COLUMNS, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                tags.add((Tag.getTagFromCursor(cursor)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return tags;
    }

    public String getAllTagsString(){
        List<Tag> mCurrentNoteTags;
        StringBuilder s = new StringBuilder("");
        mCurrentNoteTags = getAllTags();
        for (int i=0; i<mCurrentNoteTags.size(); i++) {
            s.append(mCurrentNoteTags.get(i).getTag());
            s.append(";");
        }
        return s.toString();
    }

    public void addTags(List<Tag> tags){
        for (int i=0; i<tags.size(); i++) {
            create(tags.get(i).getTag());
        }
    }

    public Tag getTag(Long id){
        Tag tag;
        Cursor cursor = mContext.getContentResolver().query(NoteContentProvider.CONTENT_URI_TAG,
                Constag.COLUMNS, Constag.COLUMN_ID + "=" +id,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            tag = Tag.getTagFromCursor(cursor);
            return tag;
        }
        return null;
    }

    public Long getTagId(String name){
        Tag tag;
        Cursor cursor = mContext.getContentResolver().query(NoteContentProvider.CONTENT_URI_TAG,
                Constag.COLUMNS, Constag.COLUMN_NAME + "=" +name,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
            tag = Tag.getTagFromCursor(cursor);
            return tag.getId();
        }
        else {
            return create(name);
        }
    }

    public void delete(Tag tag){
        mContext.getContentResolver().delete(NoteContentProvider.CONTENT_URI_TAG,Constag.COLUMN_ID + "=" + tag.getId(), null);
    }

}
