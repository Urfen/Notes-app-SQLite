package se.arvidbodkth.laboration41.SQLitePackage;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import se.arvidbodkth.laboration41.NotePackage.Note;


/**
 * Created by Arvid on 2016-01-05.
 *
 */
public class NoteTask extends AsyncTask<Void, Integer, ArrayList<Note>> {

    private NoteDbHelper mDbHelper;
    private String query;

    private ArrayList<Note> noteList;
    private Note note;

    private Activity activity;

    public NoteTask(Activity activity, Context context, String query, Note note){
        this.activity = activity;
        this.query = query;
        this.note = note;

        mDbHelper = new NoteDbHelper(context);
    }

    @Override
    protected ArrayList<Note> doInBackground(Void... params) {
        switch (query){
            case "ADD":
                mDbHelper.addNote(this.note);
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
        if(activity != null){
            activity.finish();
        }
    }
}
