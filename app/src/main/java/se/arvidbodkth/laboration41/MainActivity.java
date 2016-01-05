package se.arvidbodkth.laboration41;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import se.arvidbodkth.laboration41.NotePackage.Note;
import se.arvidbodkth.laboration41.NotePackage.NoteModel;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RadioButton titleButton, dateButton, bodyButton;

    private NoteModel model;

    private ArrayAdapter<Note> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new NoteModel(this.getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                intent.putExtra("model",model);
                startActivityForResult(intent, 1);
            }
        });

        model.testNotes();

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<Note>(this,android.R.layout.simple_list_item_1,model.getNoteList());
        titleButton = (RadioButton) findViewById(R.id.titleButton);
        dateButton = (RadioButton) findViewById(R.id.dateButton);
        bodyButton = (RadioButton) findViewById(R.id.bodyButton);

        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        model.updateList();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
