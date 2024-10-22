<%-- 
    Document   : register
    Created on : 18 thg 10, 2024, 01:09:23
    Author     : mituz
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<h2>Register</h2>

<form action="${pageContext.request.contextPath}/register" method="post">
    Full Name: <input type="text" name="fullName" required><br>
    Username: <input type="text" name="userName" required><br>
    Email: <input type="email" name="email" required><br>
    Mobile: <input type="text" name="mobile" required><br>
    Password: <input type="password" name="password" required><br>
    <input type="submit" value="Register">
</form>

<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<a href="login.jsp">Already have an account? Login here</a>

</body>
</html>

