package com.bean.wtodo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bean.wtodo.dto.Note;

import java.util.List;

import static android.R.color.holo_green_light;
import static android.R.color.holo_orange_light;
import static android.R.color.holo_red_light;

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
        TextView tvPriority = (TextView) itemView.findViewById(R.id.tvPri);
        Note note = mNoteList.get(position);
        tvContent.setText(note.getNoteTitle());
        switch (note.getNotePriority()){
            case 0:
                tvPriority.setText("HIGH");
                tvPriority.setTextColor(ContextCompat.getColor(getContext(), R.color.colorHigh));
                break;
            case 1:
                tvPriority.setText("MEDIUM");
                tvPriority.setTextColor(ContextCompat.getColor(getContext(), R.color.colorMedium));
                break;
            case 2:
                tvPriority.setText("LOW");
                tvPriority.setTextColor(ContextCompat.getColor(getContext(), R.color.colorLow));
                break;
        }
        return itemView;
    }
}
