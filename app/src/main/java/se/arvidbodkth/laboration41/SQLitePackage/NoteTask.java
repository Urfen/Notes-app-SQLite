package se.arvidbodkth.laboration41.SQLitePackage;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import se.arvidbodkth.laboration4.NotePackage.Note;

/**
 * Created by Arvid on 2016-01-05.
 *
 */
public class NoteTask extends AsyncTask<ArrayList<Note>, Integer, ArrayList<Note>> {

    private NoteDbHelper mDbHelper;
    private String query;

    private ArrayList<Note> noteList;


    public NoteTask(Context context, String query){
        noteList = new ArrayList<>();
        mDbHelper = new NoteDbHelper(context);
        this.query = query;
    }

    @Override
    protected ArrayList<Note> doInBackground(ArrayList<Note>... params) {
        if(query.equals("First")){
            noteList.add(mDbHelper.getFirst());
            return noteList;
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
    }
}
