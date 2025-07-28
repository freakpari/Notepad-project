package com.example.notepad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcNoteDao implements NoteDao {
    public JdbcNoteDao() {
        try {
            Class.forName("org.h2.Driver");
            try (Connection connection = getConnection()) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS notes (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "title VARCHAR(255), " +
                        "content TEXT)");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:~/notepad-db", "sa", "sa");
    }

    @Override
    public void save(Note note) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO notes (title, content) VALUES (?, ?)");
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> findAll() {
        List<Note> notes = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM notes");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Note note = new Note();
                note.setId(resultSet.getInt("id"));
                note.setTitle(resultSet.getString("title"));
                note.setContent(resultSet.getString("content"));
                notes.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

}
