package se.arvidbodkth.laboration41;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import java.io.IOException;

import se.arvidbodkth.laboration41.NotePackage.NoteState;
import se.arvidbodkth.laboration41.NotePackage.SaveNoteState;

/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * Class for editing a note.
 */
public class EditNoteActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 0;
    private String imageURI = "ingen bild";
    private boolean noteIsSaved = false;
    private ImageButton imageButton;
    private EditText titleText, dateText, bodyText;
    private String id;

    /**
     * When the activity starts the view and ints components are instantiated.
     * Then an intent it uses that info to fill in the fields.
     * @param savedInstanceState the save state.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleText = (EditText) findViewById(R.id.title);
        dateText = (EditText) findViewById(R.id.dateText);
        bodyText = (EditText) findViewById(R.id.body);
        imageButton = (ImageButton) findViewById(R.id.viewImageButton);


        id = getIntent().getStringExtra("ID");
        titleText.setText(getIntent().getStringExtra("TITLE"));
        dateText.setText(getIntent().getStringExtra("DATE"));
        bodyText.setText(getIntent().getStringExtra("BODY"));
        imageURI = (getIntent().getStringExtra("IMAGE"));

    }

    /**
     * When the activirty is stoped, save the current info in the note.
     */
    @Override
    protected void onStop() {
        System.out.println("stop");
        if (!noteIsSaved) {
            try {
                new SaveNoteState().writeFile(this.getApplicationContext(), new NoteState(
                        "edit"
                        , id
                        , titleText.getText().toString()
                        , dateText.getText().toString()
                        , bodyText.getText().toString()
                        , imageURI
                ));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onStop();
    }

    /**
     * When the backButton is pressed make the note not save and exit the activity.
     */
    @Override
    public void onBackPressed() {
        noteIsSaved = true;
        finish();
    }

    /**
     * Save the note by sending an intent to the mainActivity with the info.
     * @param view the view.
     */
    public void saveButtonClicked(View view) {
        if(titleText.getText().toString().trim().length() > 0
                || bodyText.getText().toString().trim().length() > 0){

            Intent intent = new Intent();

            intent.putExtra("ID", id);
            intent.putExtra("TITLE",titleText.getText().toString());
            intent.putExtra("DATE",dateText.getText().toString());
            intent.putExtra("BODY",bodyText.getText().toString());
            intent.putExtra("IMAGE",imageURI);

            setResult(Activity.RESULT_OK, intent);
            noteIsSaved = true;
            finish();
        }
    }

    /**
     * When the showImage button is pressed the uri is checked so that it contains
     * an image.
     * @param view the view.
     */
    public void viewImageButtonClicked(View view){
        if(imageURI.toLowerCase().contains("content")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(imageURI)));
        }
    }

    /**
     * Code from http://codetheory.in/android-pick-select-image-from-gallery-with-intents/
     * was used to make the correct intent and open whe Android image chooser.
     * @param view the view
     */
    public void imageButtonClicked(View view){
        //http://codetheory.in/android-pick-select-image-from-gallery-with-intents/

        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    /**
     * Code from http://codetheory.in/android-pick-select-image-from-gallery-with-intents/
     * was used to get the uri from the selected image.
     * @param requestCode what the request was.
     * @param resultCode the result
     * @param data the datae from the intent.
     */
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
