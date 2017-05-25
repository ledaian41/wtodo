package com.bean.wtodo.dto;

import java.io.Serializable;

/**
 * Created by Lê Đại An on 25-May-17.
 */

public class Note implements Serializable {
    private int noteId;
    private String noteContent;

    public Note() {
    }

    public Note(int noteId, String noteContent) {
        this.noteId = noteId;
        this.noteContent = noteContent;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
