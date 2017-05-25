package com.bean.wtodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bean.wtodo.dto.Note;

import java.util.List;

/**
 * Created by Lê Đại An on 25-May-17.
 */

public class NoteAdapter extends ArrayAdapter<Note> {

    private List<Note> mNoteList;

    public NoteAdapter(Context context, List<Note> noteList) {
        super(context, -1);
        mNoteList = noteList;
    }

    @Override
    public int getCount() {
        return mNoteList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        }
        TextView tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        Note note = mNoteList.get(position);
        tvContent.setText(note.getNoteContent());
        return itemView;
    }
}
