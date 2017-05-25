package com.bean.wtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bean.wtodo.dto.Note;

import java.io.Serializable;

public class EditItemActivity extends AppCompatActivity {

    private EditText editText;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        //Find reference to those views
        editText = (EditText) findViewById(R.id.editText);
        //Set value to text field
        note = (Note) getIntent().getSerializableExtra("item");
        editText.setText(note.getNoteContent());
        editText.setSelection(note.getNoteContent().length());
    }

    public void onSave(View view) {
        Intent data = new Intent();
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        String content = editText.getText().toString();
        note.setNoteContent(content);
        db.updateNote(note);
        setResult(RESULT_OK, data);
        this.finish();
    }

    public void onCancel(View view) {
        this.onBackPressed();
    }
}
