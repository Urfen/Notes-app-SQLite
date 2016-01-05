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

    public void testNotes(){
        for (int i = 0; i < 10; i++) {
            noteList.add(
                    new Note(
                            String.valueOf(i),
                            "Title " + i,
                            "Date 21",
                            "This is the body of the note... . ... . . .. . .. . . . . . . . . . . . .",
                            "img path here"
                            )
            );

            for (Note n: noteList) {
                noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"ADD",n);
                noteTask.execute();
            }
        }
    }

    public ArrayList<Note> getNoteList(){
        return noteList;
    }

    public void setNoteList(ArrayList<Note> notes){
        noteList.clear();
        noteList.addAll(notes);
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

}
