package com.example.android.blocodenotas.models;

        import android.database.Cursor;
        import com.example.android.blocodenotas.utility.Constrel;

/**
 * Created by Murilo on 31-Mar-16.
 */
public class Rel {
    private long id, note_id, tag_id;
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }
    public void setNoteId(Long id){
        this.id = id;
    }
    public Long getNoteId(){
        return this.id;
    }
    public void setTagId(Long id){
        this.id = id;
    }
    public Long getTagId(){
        return this.id;
    }
    public static Rel getRelFromCursor(Cursor cursor) {
        Rel rel = new Rel();
        rel.setId(cursor.getLong(cursor.getColumnIndex(Constrel.COLUMN_ID)));
        rel.setTagId(cursor.getLong(cursor.getColumnIndex(Constrel.COLUMN_TAG)));
        rel.setNoteId(cursor.getLong(cursor.getColumnIndex(Constrel.COLUMN_NOTE)));
        return rel;
    }

}
