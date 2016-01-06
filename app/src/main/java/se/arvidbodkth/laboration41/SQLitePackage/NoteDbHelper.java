package se.arvidbodkth.laboration41.SQLitePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import se.arvidbodkth.laboration41.NotePackage.Note;


/**
 * Created by Arvid on 2016-01-04.
 *
 */
public class NoteDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    private SQLiteDatabase db;

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " +
            NoteContract.NoteEntry.TABLE_NAME + " (" +
            NoteContract.NoteEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NoteContract.NoteEntry.NOTE_TITLE + " TEXT," +
            NoteContract.NoteEntry.NOTE_DATE + " TEXT," +
            NoteContract.NoteEntry.NOTE_BODY + " TEXT," +
            NoteContract.NoteEntry.IMAGE_NAME + " TEXT" +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteContract.NoteEntry.TABLE_NAME;

    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void removeAll() {
        db = this.getWritableDatabase();
        db.delete(NoteContract.NoteEntry.TABLE_NAME, null, null);
        db.close();
    }

    public void removeOne(String id){
        db = this.getWritableDatabase();

        db.delete(NoteContract.NoteEntry.TABLE_NAME, NoteContract.NoteEntry.COLUMN_NAME_ID + "=?" , new String[] {id});
        db.close();

        getAll();
    }

    public ArrayList<Note> getAll() {
        ArrayList<Note> notes = new ArrayList<>();
        db = this.getReadableDatabase();

        String[] projection = {
                NoteContract.NoteEntry.COLUMN_NAME_ID,
                NoteContract.NoteEntry.NOTE_TITLE,
                NoteContract.NoteEntry.NOTE_DATE,
                NoteContract.NoteEntry.NOTE_BODY,
                NoteContract.NoteEntry.IMAGE_NAME
        };

        Cursor c = db.query(
                NoteContract.NoteEntry.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        c.moveToFirst();

        while (!c.isAfterLast()) {
            System.out.println(c.toString());
            notes.add(new Note(
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.COLUMN_NAME_ID)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_TITLE)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_DATE)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_BODY)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.IMAGE_NAME))
            ));

            c.moveToNext();
        }

        for (Note n : notes) {
            System.out.println(n.toString());
        }

        c.close();
        db.close();
        return notes;
    }

    public void addNote(Note note) {

        db = this.getWritableDatabase();

       /* ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.NOTE_TITLE, note.getTitle());
        values.put(NoteContract.NoteEntry.NOTE_DATE, note.getDate());
        values.put(NoteContract.NoteEntry.NOTE_BODY, note.getBody());
        values.put(NoteContract.NoteEntry.IMAGE_NAME, note.getImageName());*/

        db.execSQL("INSERT INTO NOTE (TITLE, DATE, CONTENT, IMAGENAME) VALUES ('"
                        + note.getTitle() + "', '"
                        + note.getDate() + "', '"
                        + note.getBody() + "', '"
                        + note.getImageName() + "')"
        );
                /*insert(
                NoteContract.NoteEntry.TABLE_NAME,
                null,
                values);*/
        System.out.println("INSERT INTO NOTES (NOTE_TITLE, NOTE_DATE, NOTE_BODY, IMAGE_NAME) VALUES ("
                + note.getTitle() + ", "
                + note.getDate() + ", "
                + note.getBody() + ", "
                + note.getImageName() + ")");

        db.close();
    }

    public ArrayList<Note> searchTitle(String param){
        System.out.println("Searching for: " + param);
        ArrayList<Note> notes = new ArrayList<>();
        db = this.getReadableDatabase();

        String[] projection = {
                NoteContract.NoteEntry.COLUMN_NAME_ID,
                NoteContract.NoteEntry.NOTE_TITLE,
                NoteContract.NoteEntry.NOTE_DATE,
                NoteContract.NoteEntry.NOTE_BODY,
                NoteContract.NoteEntry.IMAGE_NAME
        };

        Cursor c = db.query(
                NoteContract.NoteEntry.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                NoteContract.NoteEntry.NOTE_TITLE
                        + " LIKE '%" + param + "%'",       // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        c.moveToFirst();

        while (!c.isAfterLast()) {
            System.out.println(c.toString());
            notes.add(new Note(
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.COLUMN_NAME_ID)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_TITLE)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_DATE)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_BODY)),
                    c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.IMAGE_NAME))
            ));

            c.moveToNext();
        }

        for (Note n : notes) {
            System.out.println(n.toString());
        }

        c.close();
        db.close();
        return notes;
    }

    public ArrayList<Note>  searchDate(String param){
        return null;
    }

    public ArrayList<Note>  searchBody(String param){
        return null;
    }

}
