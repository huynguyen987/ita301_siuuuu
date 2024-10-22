<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h2>User List</h2>

<table border="1">
    <tr>
        <th>User ID</th>
        <th>Full Name</th>
        <th>Username</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userId}</td>
            <td>${user.fullName}</td>
            <td>${user.userName}</td>
            <td>${user.email}</td>
            <td>
                <a href="user-details?id=${user.userId}">View Details</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${empty users}">
    <p>No users found.</p>
</c:if>

</body>
</html>
