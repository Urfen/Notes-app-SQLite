package se.arvidbodkth.laboration41;

import android.content.Context;
import android.os.Environment;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Arvid on 2016-01-08.
 *
 */
public class SaveNoteState {

    private static final String FILE_NAME = "NoteStateFile";

    //Attempts to read the saved file
    public Object readFile(Context context)throws IOException, ClassNotFoundException{
        FileInputStream fileIn = context.openFileInput(FILE_NAME);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Object noteStateFile = (NoteState) in.readObject();
        context.deleteFile(FILE_NAME);
        return noteStateFile;
    }

    //Attempts to write a file
    public void writeFile(Context context, NoteState state) throws IOException{

        FileOutputStream fileOut= context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(state);
        out.close();
        fileOut.close();
    }
}
