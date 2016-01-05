package se.arvidbodkth.laboration41.NotePackage;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import se.arvidbodkth.laboration41.SQLitePackage.NoteTask;

/**
 * Created by Arvid on 2016-01-05.
 *
 */
public class NoteModel{

    private NoteTask noteTask;
    private Context context;

    private ArrayList<Note> noteList;

    public NoteModel(Context context){
        noteList = new ArrayList<>();
        this.context = context;
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
        }
    }

    public ArrayList<Note> getNoteList(){
        return noteList;
    }

    public void setNoteList(ArrayList<Note> noteList){
        this.noteList.addAll(noteList);
    }

    public void updateList(){

    }

    public void addNote(Note note){
        noteTask = new NoteTask(this,context,"ADD",note);
    }

}
