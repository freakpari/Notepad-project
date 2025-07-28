package com.example.notepad;

import java.util.List;

public interface NoteDao {
    void save(Note note);
    List<Note> findAll();

}
