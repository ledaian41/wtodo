package com.bean.wtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bean.wtodo.dto.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Note> noteList = new ArrayList<Note>();
    private ArrayAdapter<Note> listViewAdapter;
    private ListView lvWork;
    private final int REQUEST_CODE = 20;
    private android.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create the layout in memory
        setContentView(R.layout.activity_main);
        //View are created in the layout
        //Find reference to those views
        lvWork = (ListView) findViewById(R.id.lvWork);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<Note> list = db.getAllNote();
        this.noteList.addAll(list);
        this.listViewAdapter = new NoteAdapter(MainActivity.this, noteList);
        lvWork.setAdapter(listViewAdapter);
        setupListViewListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            refreshView();
        }
    }

    private void refreshView() {
        MyDatabaseHelper db = new MyDatabaseHelper(MainActivity.this);
        noteList.clear();
        List<Note> list = db.getAllNote();
        noteList.addAll(list);
        listViewAdapter.notifyDataSetChanged();
    }

    private void setupListViewListener() {
        lvWork.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MyDatabaseHelper db = new MyDatabaseHelper(MainActivity.this);
                db.deleteNote(noteList.get(position));
                refreshView();
                //Show alert
                Toast.makeText(MainActivity.this, "Done !!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        lvWork.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("item", noteList.get(position));
                intent.putExtra("action", "update");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    public void onSubmit(View view) {
        Note note = new Note();
        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
        intent.putExtra("item", note);
        intent.putExtra("action", "create");
        startActivityForResult(intent, REQUEST_CODE);
    }
}
