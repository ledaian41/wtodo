package com.bean.wtodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bean.wtodo.dto.Note;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    private final List<Note> noteList = new ArrayList<Note>();
    private ArrayAdapter<Note> listViewAdapter;
    private EditText etWork;
    private ListView lvWork;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create the layout in memory
        setContentView(R.layout.activity_main);
        //View are created in the layout
        //Find reference to those views
        lvWork = (ListView) findViewById(R.id.lvWork);
        etWork = (EditText) findViewById(R.id.etWork);

        MyDatabaseHelper db = new MyDatabaseHelper(this);

        List<Note> list = db.getAllNote();
        this.noteList.addAll(list);
//      this.listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.noteList);
        this.listViewAdapter = new NoteAdapter(MainActivity.this, noteList);
        lvWork.setAdapter(listViewAdapter);
        etWork = (EditText) findViewById(R.id.etWork);
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
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    public void onSubmit(View view) {
        //Get value from the text field
        String content = etWork.getText().toString();
        Note note = new Note();
        note.setNoteContent(content);
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.addNote(note);
        refreshView();
        //Clear value in text field
        etWork.setText("");
    }
}
