package se.arvidbodkth.laboration41;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * Class for reading a note.
 */
public class ReadNoteActivity extends AppCompatActivity {

    private String imageURI = "ingen bild";
    private TextView titleText, dateText, bodyText;
    private String id;
    private static final int RESULT_EDIT = 10;
    private static final int RESULT_REMOVE = 11;

    /**
     * When the activity starts the view and ints components are instantiated.
     * Then an intent it uses that info to fill in the fields.
     * @param savedInstanceState the save state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleText = (TextView) findViewById(R.id.titleText);
        dateText = (TextView) findViewById(R.id.dateText);
        bodyText = (TextView) findViewById(R.id.bodyText);

        id = getIntent().getStringExtra("ID");
        titleText.setText(getIntent().getStringExtra("TITLE"));
        dateText.setText(getIntent().getStringExtra("DATE"));
        bodyText.setText(getIntent().getStringExtra("BODY"));
        bodyText.setMovementMethod(new ScrollingMovementMethod());
        imageURI = (getIntent().getStringExtra("IMAGE"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titleText.getText().toString().trim().length() > 0
                        || bodyText.getText().toString().trim().length() > 0){

                    Intent intent = new Intent();

                    intent.putExtra("ID",id);
                    intent.putExtra("TITLE",titleText.getText().toString());
                    intent.putExtra("DATE",dateText.getText().toString());
                    intent.putExtra("BODY",bodyText.getText().toString());
                    intent.putExtra("IMAGE",imageURI);

                    setResult(RESULT_EDIT, intent);
                    finish();
                }
            }
        });
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
     * Creates the optionsMenu.
     * @param menu the menu to create.
     * @return true.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read, menu);
        return true;
    }

    /**
     * When an item in the options menu is selected.
     * @param item the item.
     * @return true.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_remove) {
            Intent intent = new Intent();
            intent.putExtra("ID",this.id);
            setResult(RESULT_REMOVE, intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
