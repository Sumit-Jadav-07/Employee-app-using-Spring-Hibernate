<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
</head>
<body>
    <div class="signup-container">
        <h2>Signup</h2>
        <form action="signup" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name">
                <span class="error-details">${fullnameError}</span>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email">
                <span class="error-details">${emailError}</span>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password">
                <span class="error-details">${passError}</span>
            </div>
            <button type="submit">Signup</button>
        </form>
    </div>
</body>
</html>
