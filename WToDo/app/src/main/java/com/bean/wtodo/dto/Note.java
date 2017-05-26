package com.bean.wtodo.dto;

import java.io.Serializable;
import java.text.DateFormat;


/**
 * Created by Lê Đại An on 25-May-17.
 */

public class Note implements Serializable {
    private int noteId;
    private String noteTitle;
    private String noteContent;
    private int notePriority;

    public Note() {
    }

    public Note(int noteId, String noteTitle, String noteContent, int notePriority) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.notePriority = notePriority;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public int getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(int notePriority) {
        this.notePriority = notePriority;
    }
}
