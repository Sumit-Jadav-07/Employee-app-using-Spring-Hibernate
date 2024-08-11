<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listuser.css">
</head>
<body>
    <h1>User Details</h1>
    <table class="user-table">
        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>
                    <a href="viewuser?userId=${user.userId}" class="btn view-btn">View</a>
                    <a href="edituserpage?userId=${user.userId}" class="btn edit-btn">Edit</a>
                    <a href="deleteuser?userId=${user.userId}" class="btn delete-btn">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>


