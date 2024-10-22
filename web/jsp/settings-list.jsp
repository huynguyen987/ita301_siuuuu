<%@ page import="entities.Setting" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings List</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h1>Settings List</h1>

    <div class="filter-section">
        <select id="filter-type">
            <option value="">Filter by Type</option>
            <option value="Type 1">Type 1</option>
            <option value="Type 2">Type 2</option>
        </select>

        <select id="filter-status">
            <option value="">Filter by Status</option>
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
        </select>
    </div>

    <div class="search-section">
        <input type="text" id="search-keyword" placeholder="Search by name or value...">
    </div>

    <table>
        <thead>
            <tr>
                <th>Setting Name</th>
                <th>Value</th>
                <th>Type</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Setting> settings = (List<Setting>) request.getAttribute("settings");
                for (Setting setting : settings) {
            %>
            <tr>
                <td><%= setting.getName() %></td>
                <td><%= setting.getValue() %></td>
                <td><%= setting.getType() %></td>
                <td class="<%= setting.getStatus() ? "status-active" : "status-inactive" %>">
                    <%= setting.getStatus() ? "Active" : "Inactive" %>
                </td>
                <td>
                    <form method="post" action="settings">
                        <input type="hidden" name="id" value="<%= setting.getSettingId() %>">
                        <button type="submit" name="action" value="edit" class="btn">Edit</button>
                        <button type="submit" name="action" value="<%= setting.getStatus() ? "deactivate" : "activate" %>"
                                class="btn <%= setting.getStatus() ? "btn-danger" : "btn-success" %>">
                            <%= setting.getStatus() ? "Deactivate" : "Activate" %>
                        </button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <a href="/jsp/setting-detail.jsp" class="btn">New Setting</a>
</div>
</body>
</html>
