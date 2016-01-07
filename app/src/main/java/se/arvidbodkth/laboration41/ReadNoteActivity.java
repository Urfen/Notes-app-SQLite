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
import android.widget.ImageView;
import android.widget.TextView;

public class ReadNoteActivity extends AppCompatActivity {

    private String imageURI = "ingen bild";

    private TextView titleText, dateText, bodyText;
    private ImageView imageView;

    private String id;

    private static final int RESULT_EDIT = 10;
    private static final int RESULT_REMOVE = 11;

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
        imageView = (ImageView) findViewById(R.id.imageView2);

        id = getIntent().getStringExtra("ID");
        titleText.setText(getIntent().getStringExtra("TITLE"));
        dateText.setText(getIntent().getStringExtra("DATE"));
        bodyText.setText(getIntent().getStringExtra("BODY"));
        bodyText.setMovementMethod(new ScrollingMovementMethod());
        imageURI = (getIntent().getStringExtra("IMAGE"));


        imageView.setImageURI(Uri.parse(imageURI));
        System.out.println("här");

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read, menu);
        return true;
    }

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
