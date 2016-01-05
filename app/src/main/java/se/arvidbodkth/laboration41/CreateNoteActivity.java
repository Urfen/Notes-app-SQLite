package se.arvidbodkth.laboration41;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Date;


import se.arvidbodkth.laboration41.NotePackage.Note;
import se.arvidbodkth.laboration41.NotePackage.NoteModel;
import se.arvidbodkth.laboration41.SQLitePackage.NoteContract;
import se.arvidbodkth.laboration41.SQLitePackage.NoteDbHelper;
import se.arvidbodkth.laboration41.SQLitePackage.NoteTask;

public class CreateNoteActivity extends AppCompatActivity {


    private int PICK_IMAGE_REQUEST = 0;
    private String imageURI = "ingen bild";


    private EditText titleText, dateText, bodyText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleText = (EditText) findViewById(R.id.title);
        dateText = (EditText) findViewById(R.id.dateText);
        bodyText = (EditText) findViewById(R.id.body);
        imageView = (ImageView) findViewById(R.id.imageView);

        dateText.setText(new Date().toString());

    }

    public void saveButtonClicked(View view) {
        if(titleText.getText() != null && dateText.getText() != null && bodyText.getText() != null){

            Intent intent = new Intent();

            intent.putExtra("TITLE",titleText.getText().toString());
            intent.putExtra("DATE",dateText.getText().toString());
            intent.putExtra("BODY",bodyText.getText().toString());
            intent.putExtra("IMAGE",imageURI);

            setResult(Activity.RESULT_OK, intent);
            finish();

            /*SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(NoteContract.NoteEntry.COLUMN_NAME_ID, "1");
            values.put(NoteContract.NoteEntry.NOTE_TITLE, titleText.getText().toString());
            values.put(NoteContract.NoteEntry.NOTE_DATE,  dateText.getText().toString());
            values.put(NoteContract.NoteEntry.NOTE_BODY, bodyText.getText().toString());
            values.put(NoteContract.NoteEntry.IMAGE_NAME, imageURI);

            long newRowId;
            newRowId = db.insert(
                    NoteContract.NoteEntry.TABLE_NAME,
                    null,
                    values);*/

        }
    }


    public void imageButtonClicked(View view){
        //http://codetheory.in/android-pick-select-image-from-gallery-with-intents/

        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    //http://codetheory.in/android-pick-select-image-from-gallery-with-intents/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageURI = uri.toString();
                System.out.println(uri);

                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
