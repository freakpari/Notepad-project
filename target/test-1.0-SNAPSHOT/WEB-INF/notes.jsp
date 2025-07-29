<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.notepad.Note" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>📝 Notes App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h3>📒 Your Notes</h3>
            <%
                List<Note> notes = (List<Note>) request.getAttribute("notes");
                if (notes != null && !notes.isEmpty()) {
                    for (Note note : notes) {
            %>
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title"><%= note.getTitle() %></h5>
                    <p class="card-text"><%= note.getContent() %></p>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <div class="alert alert-info">There is no note</div>
            <%
                }
            %>
        </div>

        <div class="col-md-6">
            <h3>➕ New Note</h3>
            <form action="notes" method="post" class="p-4 bg-white shadow rounded">
                <div class="mb-3">
                    <label for="title" class="form-label">Title:</label>
                    <input type="text" id="title" name="title" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">Content:</label>
                    <textarea id="content" name="content" rows="5" class="form-control" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Save Note</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
