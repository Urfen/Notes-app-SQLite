package se.arvidbodkth.laboration41;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import java.io.IOException;
import se.arvidbodkth.laboration41.NotePackage.Note;
import se.arvidbodkth.laboration41.NotePackage.NoteModel;
import se.arvidbodkth.laboration41.NotePackage.*;

/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * Class for the mainActivity that contains the main view and its
 * components.
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RadioButton titleButton, dateButton, bodyButton;
    private EditText searchText;
    private NoteModel model;
    private ArrayAdapter<Note> adapter;

    private static final int CREATE_NOTE_REQUEST = 1;
    private static final int EDIT_NOTE_REQUEST = 2;
    private static final int READ_NOTE_REQUEST = 3;
    private static final int RESULT_EDIT = 10;
    private static final int RESULT_REMOVE = 11;

    /**
     * When the activity starts create the model, set up the view components then
     * if there was a note adit or creation in progress that one is loaded in its
     * correct activity.
     * @param savedInstanceState the saveState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new NoteModel(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivityForResult(intent, CREATE_NOTE_REQUEST);
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_expandable_list_item_1
                , model.getNoteList());
        titleButton = (RadioButton) findViewById(R.id.titleButton);
        dateButton = (RadioButton) findViewById(R.id.dateButton);
        bodyButton = (RadioButton) findViewById(R.id.bodyButton);
        searchText = (EditText) findViewById(R.id.searchText);

        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        setHandlers();

        model.updateNoteList();

        try{
            NoteState state;

            state = (NoteState) new SaveNoteState().readFile(this.getApplicationContext());

            if (state.getState().equals("create")){
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                intent.putExtra("TITLE", state.getTitle());
                intent.putExtra("DATE", state.getDate());
                intent.putExtra("BODY", state.getBody());
                intent.putExtra("IMAGE", state.getImageName());
                startActivityForResult(intent, CREATE_NOTE_REQUEST);
            }

            if (state.getState().equals("edit")){
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("ID", state.getId());
                intent.putExtra("TITLE", state.getTitle());
                intent.putExtra("DATE", state.getDate());
                intent.putExtra("BODY", state.getBody());
                intent.putExtra("IMAGE", state.getImageName());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    /**
     * Create the context menu for when an item in the listView is held in.
     * @param menu the menu.
     * @param v the view.
     * @param menuInfo info.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //AdapterView.AdapterContextMenuInfo item = (AdapterView.AdapterContextMenuInfo) menuInfo;
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }

    /**
     * When an item in the context menu is selected.
     * @param item the item.
     * @return true.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Note n = model.getNote(info.position);

        switch (item.getItemId()) {
            case R.id.editNote:

                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("ID", n.getId());
                intent.putExtra("TITLE", n.getTitle());
                intent.putExtra("DATE", n.getDate());
                intent.putExtra("BODY", n.getBody());
                intent.putExtra("IMAGE", n.getImageName());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);

                return true;
            case R.id.removeNote:
                model.removeNote(n.getId());
                return true;
        }
        return super.onContextItemSelected(item);

    }

    /**
     * When an activity is done make the appropriate request to the db.
     * @param requestCode the request.
     * @param resultCode the result.
     * @param data the data in the intent.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CREATE_NOTE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {

                model.addNote(new Note(
                        data.getStringExtra("TITLE"),
                        data.getStringExtra("DATE"),
                        data.getStringExtra("BODY"),
                        data.getStringExtra("IMAGE")
                ));
                System.out.println("Added new note");
            }
        }

        if (requestCode == EDIT_NOTE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {

                model.updateNote(new Note(
                        data.getStringExtra("ID"),
                        data.getStringExtra("TITLE"),
                        data.getStringExtra("DATE"),
                        data.getStringExtra("BODY"),
                        data.getStringExtra("IMAGE")
                ));
            }
        }

        if (requestCode == READ_NOTE_REQUEST) {
            if (resultCode == RESULT_EDIT) {
                Note n = new Note(data.getStringExtra("ID"),
                        data.getStringExtra("TITLE"),
                        data.getStringExtra("DATE"),
                        data.getStringExtra("BODY"),
                        data.getStringExtra("IMAGE")
                );

                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("ID", n.getId());
                intent.putExtra("TITLE", n.getTitle());
                intent.putExtra("DATE", n.getDate());
                intent.putExtra("BODY", n.getBody());
                intent.putExtra("IMAGE", n.getImageName());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        }

        if (requestCode == READ_NOTE_REQUEST) {
            if (resultCode == RESULT_REMOVE) {
                model.removeNote(data.getStringExtra("ID"));
                System.out.println("REMOVES " + data.getStringExtra("ID"));
            }
        }
    }

    /**
     * Creates the optionsMenu.
     * @param menu the menu.
     * @return true.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            model.removeAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Update the listView with the current data.
     */
    public void update() {
        adapter.notifyDataSetChanged();
    }

    /**
     * Sets up the handlers for the view componets.
     */
    private void setHandlers() {

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (titleButton.isChecked()) {
                    model.searchTitle(searchText.getText().toString());
                } else if (dateButton.isChecked()) {
                    model.searchDate(searchText.getText().toString());
                } else if (bodyButton.isChecked()) {
                    model.searchBody(searchText.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Note n = (Note) listView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, ReadNoteActivity.class);
                intent.putExtra("ID", n.getId());
                intent.putExtra("TITLE", n.getTitle());
                intent.putExtra("DATE", n.getDate());
                intent.putExtra("BODY", n.getBody());
                intent.putExtra("IMAGE", n.getImageName());
                startActivityForResult(intent, READ_NOTE_REQUEST);


            }
        });

    }
}
