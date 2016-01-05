package se.arvidbodkth.laboration41;

import java.util.ArrayList;

import se.arvidbodkth.laboration41.SQLitePackage.NoteTask;

/**
 * Created by Arvid on 2016-01-05.
 *
 */
public class NoteModel {

    private NoteTask noteTask;

    private ArrayList<Note> noteList;

    public NoteModel(){
        noteList = new ArrayList<>();

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
}
