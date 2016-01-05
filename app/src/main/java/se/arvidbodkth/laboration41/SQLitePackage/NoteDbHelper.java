package se.arvidbodkth.laboration41.SQLitePackage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import se.arvidbodkth.laboration41.Note;


/**
 * Created by Arvid on 2016-01-04.
 *
 */
public class NoteDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    private SQLiteDatabase db;

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " +
            NoteContract.NoteEntry.TABLE_NAME + " (" +
            NoteContract.NoteEntry._ID + " INTEGER PRIMARY KEY," +
            NoteContract.NoteEntry.COLUMN_NAME_ID + " TEXT," +
            NoteContract.NoteEntry.NOTE_TITLE + " TEXT," +
            NoteContract.NoteEntry.NOTE_DATE + " TEXT," +
            NoteContract.NoteEntry.NOTE_BODY + " TEXT," +
            NoteContract.NoteEntry.IMAGE_NAME + " TEXT" +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteContract.NoteEntry.TABLE_NAME;

    public NoteDbHelper(Context context){
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

    public Note getFirst(){
        db = this.getReadableDatabase();

        String[] projection = {
                NoteContract.NoteEntry._ID,
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

        return new Note(
                c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry._ID)),
                c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_TITLE)),
                c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_DATE)),
                c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.NOTE_BODY)),
                c.getString(c.getColumnIndexOrThrow(NoteContract.NoteEntry.IMAGE_NAME))
                );
    }
}
