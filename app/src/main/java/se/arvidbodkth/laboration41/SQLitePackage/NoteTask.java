package se.arvidbodkth.laboration41.SQLitePackage;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import se.arvidbodkth.laboration41.NotePackage.Note;
import se.arvidbodkth.laboration41.NotePackage.NoteModel;


/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * A Class for AsyncTask that makes requests to the db.
 */
public class NoteTask extends AsyncTask<Void, Integer, ArrayList<Note>> {

    private NoteDbHelper mDbHelper;
    private String query;
    private ArrayList<Note> noteList;

    private Note note;
    private NoteModel model;

    public NoteTask(NoteModel model, Context context, String query, Note note){
        this.model = model;
        this.query = query;
        this.note = note;

        noteList = new ArrayList<>();
        mDbHelper = new NoteDbHelper(context);
    }

    /**
     * Makes a query to the db with the given string.
     * @param params String that is used to select a task.
     * @return ArrayList with the result from the db.
     */
    @Override
    protected ArrayList<Note> doInBackground(Void... params) {
        switch (query){
            case "ADD":
                mDbHelper.addNote(this.note);
                noteList = mDbHelper.getAll();
                break;

            case "GET_ALL":
                noteList = mDbHelper.getAll();
                break;

            case "REMOVE_ALL":
                mDbHelper.removeAll();
                noteList.clear();
                break;

            case "REMOVE_ONE":
                mDbHelper.removeOne(note.getId());
                noteList = mDbHelper.getAll();
                break;

            case "SEARCH_TITLE":
                noteList = mDbHelper.searchTitle(note.getTitle());
                break;

            case "SEARCH_DATE":
                noteList = mDbHelper.searchDate(note.getDate());
                break;

            case "SEARCH_BODY":
                noteList = mDbHelper.searchBody(note.getBody());
                break;

            case "UPDATE":
                mDbHelper.updateNote(this.note);
                noteList = mDbHelper.getAll();
        }
        return null;
    }

    /**
     * When the task is done this method updates the model with the
     * new list of notes. It then tells the model to update the UI.
     * @param notes ArrayList of notes to give the model.
     */
    @Override
    protected void onPostExecute(ArrayList<Note> notes) {
        super.onPostExecute(notes);
        model.setNoteList(noteList);
        model.updateController();
        System.out.println("Task done.");
    }
}
