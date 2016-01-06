package se.arvidbodkth.laboration41.SQLitePackage;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import se.arvidbodkth.laboration41.NotePackage.Note;
import se.arvidbodkth.laboration41.NotePackage.NoteModel;


/**
 * Created by Arvid on 2016-01-05.
 *
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
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<Note> notes) {
        super.onPostExecute(notes);
        model.setNoteList(noteList);
        model.updateController();
        System.out.println("Task done.");
    }
}
