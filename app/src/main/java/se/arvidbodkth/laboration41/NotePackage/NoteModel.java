package se.arvidbodkth.laboration41.NotePackage;


import java.util.ArrayList;
import se.arvidbodkth.laboration41.MainActivity;
import se.arvidbodkth.laboration41.SQLitePackage.NoteTask;

/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * This Class is the model class for the notes.
 */
public class NoteModel{

    private NoteTask noteTask;
    private MainActivity mainActivity;

    private ArrayList<Note> noteList;

    /**
     * Constructor tha creates a list for notes.
     * @param mainActivity MainActivity the main activity.
     */
    public NoteModel(MainActivity mainActivity){
        noteList = new ArrayList<>();
        this.mainActivity = mainActivity;
    }

    /**
     * Returns the list of notes.
     * @return ArrayList with the notes.
     */
    public ArrayList<Note> getNoteList(){
        return noteList;
    }

    /**
     * Set the list if notes.
     * @param notes ArrayList of notes to set.
     */
    public void setNoteList(ArrayList<Note> notes){
        noteList.clear();
        noteList.addAll(notes);
    }

    /**
     * Returns the a note.
     * @param id int, the note to get.
     * @return
     */
    public Note getNote(int id){
        return noteList.get(id);
    }

    /**
     * Updates the main main listView with the current noteList.
     */
    public void updateController(){
        mainActivity.update();
    }

    /**
     * Creates a AsyncTask for the SQL query UPDATE.
     * @param note Note, the note to update.
     */
    public void updateNote(Note note){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"UPDATE",note);
        noteTask.execute();
    }

    /**
     * Creates a AsyncTask  that returns all the notes from the db.
     */
    public void updateNoteList(){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"GET_ALL",null);
        noteTask.execute();
    }

    /**
     * Creates a AsyncTask that removes all notes form the db.
     */
    public void removeAll(){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"REMOVE_ALL",null);
        noteTask.execute();
    }

    /**
     * Creates a AsyncTask that adds a given note to the db.
     * @param note Note, the note to add.
     */
    public void addNote(Note note){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"ADD",note);
        noteTask.execute();
    }

    /**
     * Creates a AsyncTask that removes a given note from the db.
     * @param id String of the note to add.
     */
    public void removeNote(String id){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"REMOVE_ONE"
                ,new Note(id, null, null, null, null));
        noteTask.execute();
    }

    /**
     * Creates a AsyncTask that searches the dbs titles for a given string.
     * @param param String to search for.
     */
    public void searchTitle(String param){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"SEARCH_TITLE"
                ,new Note(null, param, null, null, null));
        noteTask.execute();
    }

    /**
     * Creates a AsyncTask that searches the dbs dates for a given string.
     * @param param String of the date to search for.
     */
    public void searchDate(String param){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"SEARCH_DATE"
                ,new Note(null, null, param, null, null));
        noteTask.execute();
    }

    /**
     * Creates a AsyncTask that searches the dbs bodys for a given string.
     * @param param String of the body to search for.
     */
    public void searchBody(String param){
        noteTask = new NoteTask(this,mainActivity.getApplicationContext(),"SEARCH_BODY"
                ,new Note(null, null, null, param, null));
        noteTask.execute();
    }

}
