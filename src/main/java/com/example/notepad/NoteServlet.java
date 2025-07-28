package com.example.notepad;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "noteServlet", urlPatterns = {"/notes"})
public class NoteServlet extends HttpServlet {

    private NoteService noteService;

    @Override
    public void init() {
        NoteDao noteDao = new NoteDaoInMemory();
        noteService = new NoteService(noteDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Note> notes = noteService.getAllNotes();
        req.setAttribute("notes", notes);
        req.getRequestDispatcher("/WEB-INF/notes.jsp").forward(req, resp);
        resp.getWriter().println("NoteServlet is working!");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        try {
            noteService.saveNote(title, content);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
        }
        resp.sendRedirect("notes");
    }
}
