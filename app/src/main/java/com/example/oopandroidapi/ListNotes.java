package com.example.oopandroidapi;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListNotes {

    private static ListNotes list = null;
    private final ArrayList<Note> notes = new ArrayList<>();
    private ListNotes() {

    }

    public static ListNotes getInstance() {
        if (list == null) {
            list = new ListNotes();
        }
        return list;
    }

    public void addNotesToList(Note note) {
        notes.add(note);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public String getImportantNotes() {
        // Here we create a comma separated String of the titles (see map) in the note list, that have a star (see filter)
        String importantNotes =  android.text.TextUtils.join(", ",
                notes.stream()
                        .filter(Note::getStar)
                        .map(Note::getTitle)
                        .collect(Collectors.toList()));

        return importantNotes;
    }

    public void deleteFirstNote() {
        if (list.getNotes().size() > 0) {
            list.getNotes().remove(0);
        }
    }
}