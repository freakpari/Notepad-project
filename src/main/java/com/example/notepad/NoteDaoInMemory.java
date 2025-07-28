package com.example.notepad;

import java.util.ArrayList;
import java.util.List;

public class NoteDaoInMemory implements NoteDao {

    private final List<Note> notes = new ArrayList<>();

    @Override
    public void save(Note note) {
        notes.add(note);
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(notes);
    }
}
