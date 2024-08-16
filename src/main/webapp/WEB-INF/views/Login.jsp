<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h1>Login</h1>
            <form action="login" method="post">
                <div class="textbox">
                    <input type="text" name="email" placeholder="Email" value="${email}">
                    <span class="error-details">${emailError}</span>
                </div>
                <div class="textbox">
                    <input type="text" name="password" placeholder="Password" value="${password}">
                    <span class="error-details">${passwordError}</span>
                </div>
                <button type="submit" class="btn">Login</button>
                <div class="signup-link">
                    <p>Don't have an account? <a href="signuppage">Sign Up</a></p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
