<%@ page import="entities.Setting" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Setting Detail</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h1>Setting Detail</h1>
    <form method="post" action="save-setting">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= request.getAttribute("setting") != null ? ((Setting) request.getAttribute("setting")).getName() : "" %>" required>

        <label for="type">Type:</label>
        <select id="type" name="type">
            <option value="User Role" <%= request.getAttribute("setting") != null && ((Setting) request.getAttribute("setting")).getType().equals("User Role") ? "selected" : "" %>>User Role</option>
            <option value="System Setting" <%= request.getAttribute("setting") != null && ((Setting) request.getAttribute("setting")).getType().equals("System Setting") ? "selected" : "" %>>System Setting</option>
        </select>

        <label for="value">Value:</label>
        <input type="text" id="value" name="value" value="<%= request.getAttribute("setting") != null ? ((Setting) request.getAttribute("setting")).getValue() : "" %>">

        <label for="priority">Priority:</label>
        <input type="number" id="priority" name="priority" value="<%= request.getAttribute("setting") != null ? ((Setting) request.getAttribute("setting")).getPriority() : "" %>">

        <label>Status:</label>
        <div class="radio-group">
            <label><input type="radio" name="status" value="true" 
                <%= request.getAttribute("setting") != null && ((Setting) request.getAttribute("setting")).getStatus() ? "checked" : "" %>> Active</label>
            <label><input type="radio" name="status" value="false" 
                <%= request.getAttribute("setting") != null && !((Setting) request.getAttribute("setting")).getStatus() ? "checked" : "" %>> Inactive</label>
        </div>

        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4"><%= request.getAttribute("setting") != null ? ((Setting) request.getAttribute("setting")).getDescription() : "" %></textarea>

        <button type="submit" class="btn">Submit</button>
    </form>
</div>
</body>
</html>
