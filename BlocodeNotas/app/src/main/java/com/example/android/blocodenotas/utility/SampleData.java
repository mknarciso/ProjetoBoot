package com.example.android.blocodenotas.utility;

/**
 * Created by Adauto on 27/03/2016.
 */

import com.example.android.blocodenotas.models.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {

    public static List<Note> getSampleNotes(){
        List<Note> sampleNotes = new ArrayList<Note>();
        List<String> tags_aux = new ArrayList<String>();

        //create the dummy note
        Note note1 = new Note();
        note1.setTitle("Lista de ELE-12");
        note1.setContent("A prova é sexta! Não esquece de fazer logo a lista!");
        tags_aux.add("sugou");
        tags_aux.add("tarefa");
        tags_aux.add("ele-12");
        note1.setTags(tags_aux);
        Calendar calendar1 = GregorianCalendar.getInstance();
        note1.setDataModified(calendar1);

        //add note1 to the list
        sampleNotes.add(note1);

        tags_aux.clear();


        //create the dummy note
        Note note2 = new Note();
        note2.setTitle("Campanha Freeletics");
        tags_aux.add("freeletics");
        tags_aux.add("#noexcuses");
        tags_aux.add("#painistemporary");
        note2.setTags(tags_aux);
        note2.setContent("Partiu 11 semana do Freeletics");

        //change the date to random time
        Calendar calendar2 = GregorianCalendar.getInstance();
        calendar2.add(Calendar.DAY_OF_WEEK, 1);
        calendar2.add(Calendar.MILLISECOND, 10005623);
        note2.setDataModified(calendar2);

        //add note2 to the list
        sampleNotes.add(note2);
        tags_aux.clear();

        //create the dummy note
        Note note3 = new Note();
        note3.setTitle("Ideia texto medium");
        tags_aux.add("ideia");
        tags_aux.add("medium");
        tags_aux.add("universidade");
        note3.setTags(tags_aux);
        note3.setContent("I will like to write a blog post about how to make money online");

        //change the date to random time
        Calendar calendar3 = GregorianCalendar.getInstance();
        calendar3.add(Calendar.DAY_OF_WEEK, 2);
        calendar3.add(Calendar.MILLISECOND, 8962422);
        note3.setDataModified(calendar3);

        //add note3 to the list
        sampleNotes.add(note3);
        tags_aux.clear();


        //create the dummy note
        Note note4 = new Note();
        note4.setTitle("Cupcake Recipe");
        tags_aux.add("food");
        tags_aux.add("yummy");
        tags_aux.add("hungry");
        note4.setTags(tags_aux);
        note4.setContent("Today I found a recipe to make cup cake from www.google.");

        //pad the date with random number of days and minute
        //so all the notes do not have the same time stamp
        Calendar calendar4 = GregorianCalendar.getInstance();
        calendar4.add(Calendar.DAY_OF_WEEK, 4);
        calendar4.add(Calendar.MILLISECOND, 49762311);
        note4.setDataModified(calendar4);

        //add note4 to the list
        sampleNotes.add(note4);
        tags_aux.clear();


        //create the dummy note
        Note note5 = new Note();
        note5.setTitle("Notes from Networking Event");
        tags_aux.add("learning");
        tags_aux.add("network");
        tags_aux.add("carreira");
        note5.setTags(tags_aux);
        note5.setContent("Today I attended a developer's networking event and it was great");

        //pad the date with two days
        //pad the date with random number of days and minute
        //so all the notes do not have the same time stamp
        Calendar calendar5 = GregorianCalendar.getInstance();
        calendar5.add(Calendar.MILLISECOND, 2351689);
        note5.setDataModified(calendar5);

        //add note5 to the list
        sampleNotes.add(note5);

        return sampleNotes;
    }

}
