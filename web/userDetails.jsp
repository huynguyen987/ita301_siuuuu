<%-- 
    Document   : userDetails
    Created on : 18 thg 10, 2024, 01:03:47
    Author     : mituz
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
</head>
<body>
<h2>User Details</h2>

<table border="1">
    <tr>
        <th>Full Name</th>
        <td>${user.fullName}</td>
    </tr>
    <tr>
        <th>Username</th>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th>Mobile</th>
        <td>${user.mobile}</td>
    </tr>
    <tr>
        <th>Status</th>
        <td>${user.status}</td>
    </tr>
</table>

<a href="user-list">Back to User List</a>

</body>
</html>
