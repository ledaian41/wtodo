package com.bean.wtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.bean.wtodo.dto.Note;

public class EditItemActivity extends AppCompatActivity {

    private EditText noteTitle;
    private EditText noteContent;
    private Spinner notePriority;
    private Note note;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        //Find reference to those views
        noteTitle = (EditText) findViewById(R.id.etTitle);
        noteContent = (EditText) findViewById(R.id.etContent);
        notePriority = (Spinner) findViewById(R.id.spinnerPriority);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        notePriority.setAdapter(adapter);
        //Set value to text field
        note = (Note) getIntent().getSerializableExtra("item");
        action = (String) getIntent().getSerializableExtra("action");
        noteTitle.setText(note.getNoteTitle());
        noteContent.setText(note.getNoteContent());
        //Set value to Spinner Priority
        switch (note.getNotePriority()){
            case 0:
                notePriority.setSelection(0);
                break;
            case 1:
                notePriority.setSelection(1);
                break;
            case 2:
                notePriority.setSelection(2);
                break;
            default:
                break;
        }

    }

    public void onSave(View view) {
        Intent data = new Intent();
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        String title = noteTitle.getText().toString();
        String content = noteContent.getText().toString();
        int priority = notePriority.getSelectedItemPosition();
        note.setNoteTitle(title);
        note.setNoteContent(content);
        note.setNotePriority(priority);
        if (action.equals("update")) {
            db.updateNote(note);
        } else {
            db.addNote(note);
        }
        setResult(RESULT_OK, data);
        this.finish();
    }

    public void onCancel(View view) {
        this.onBackPressed();
    }
}
