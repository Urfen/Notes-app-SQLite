package se.arvidbodkth.laboration41.SQLitePackage;

import android.provider.BaseColumns;

/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * A Class for the strings for the db.
 */
public class NoteContract {

    //Onödig inre klass.
    public static abstract class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_ID = "id";
        public static final String NOTE_TITLE = "title";
        public static final String NOTE_BODY = "content";
        public static final String NOTE_DATE = "date";
        public static final String IMAGE_NAME = "imageName";
    }

    public NoteContract(){}
}
