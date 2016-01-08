package se.arvidbodkth.laboration41.NotePackage;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import se.arvidbodkth.laboration41.MainActivity;
import se.arvidbodkth.laboration41.SQLitePackage.NoteTask;

/**
 * Created by Arvid on 2016-01-05.
 *
 */
public class NoteModel{

    private NoteTask noteTask;
    private MainActivity mainActivity;

    private ArrayList<Note> noteList;

    public NoteModel(MainActivity mainActivity){
        noteList = new ArrayList<>();
        this.mainActivity = mainActivity;
    }


    public ArrayList<Note> getNoteList(){
        return noteList;
    }

    public void setNoteList(ArrayList<Note> notes){
        noteList.clear();
        noteList.addAll(notes);
    }

    public void updateNote(Note note){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"UPDATE",note);
        noteTask.execute();
    }

    public void updateNoteList(){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"GET_ALL",null);
        noteTask.execute();
    }

    public void removeAll(){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"REMOVE_ALL",null);
        noteTask.execute();
    }

    public void updateController(){
        mainActivity.update();
    }

    public void addNote(Note note){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"ADD",note);
        noteTask.execute();
    }

    public void removeNote(String id){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"REMOVE_ONE"
                ,new Note(id, null, null, null, null));
        noteTask.execute();
    }

    public void searchTitle(String param){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"SEARCH_TITLE"
                ,new Note(null, param, null, null, null));
        noteTask.execute();
    }

    public void searchDate(String param){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"SEARCH_DATE"
                ,new Note(null, null, param, null, null));
        noteTask.execute();
    }

    public void searchBody(String param){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"SEARCH_BODY"
                ,new Note(null, null, null, param, null));
        noteTask.execute();
    }

}
