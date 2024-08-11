<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.entity.UserEntity" %>
<!DOCTYPE html>
<html>
<head>
    <title>View User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/viewuser.css">
</head>
<body>
  <div class="user-container">
    <h1>User Details</h1>
    <% UserEntity user = (UserEntity) request.getAttribute("user"); %>
    <table>
        <tr>
            <th>ID</th>
            <td>${user.userId}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${user.name}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${user.email}</td>
        </tr>
    </table>
  </div>
</body>
</html>
