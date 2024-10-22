<%-- 
    Document   : requirementList
    Created on : 18 thg 10, 2024, 02:58:57
    Author     : mituz
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Requirement List</title>
</head>
<body>
<h2>Requirement List</h2>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Details</th>
    </tr>
    <c:forEach var="requirement" items="${requirements}">
        <tr>
            <td>${requirement.title}</td>
            <td>${requirement.description}</td>
            <td><a href="requirement-details?reqId=${requirement.reqId}">View Details</a></td>
            <td><a href="requirement-issue?reqId=${requirement.reqId}">Add New Issue</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

