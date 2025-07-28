package com.example.notepad;

import java.util.List;

public class NoteService {

    private final NoteDao noteDao;

    public NoteService(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void saveNote(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteDao.save(note);
    }

    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }
}
