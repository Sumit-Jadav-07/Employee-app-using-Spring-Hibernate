<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/edituser.css">
</head>
<body>
    <h1>Edit User</h1>
    <form action="edituser" method="post">
        <input type="hidden" name="userId" value="${user.userId}">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${user.name}">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.email}">
        </div>
        <div class="form-group">
            <button type="submit">Update</button>
        </div>
    </form>
</body>
</html>
