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
import java.text.SimpleDateFormat;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleText = (EditText) findViewById(R.id.title);
        dateText = (EditText) findViewById(R.id.dateText);
        bodyText = (EditText) findViewById(R.id.body);


        dateText.setText(new SimpleDateFormat("HH:mm dd/MM-yyyy").format(new Date()));

    }

    public void saveButtonClicked(View view) {
        if(titleText.getText().toString().trim().length() > 0
                || bodyText.getText().toString().trim().length() > 0){

            Intent intent = new Intent();

            intent.putExtra("TITLE",titleText.getText().toString());
            intent.putExtra("DATE",dateText.getText().toString());
            intent.putExtra("BODY",bodyText.getText().toString());
            intent.putExtra("IMAGE",imageURI);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    public void viewImageButtonClicked(View view){
        if(imageURI != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(imageURI)));
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
            imageURI = uri.toString();
            System.out.println(uri);

        }
    }
}
