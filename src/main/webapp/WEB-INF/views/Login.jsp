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
                    <input type="text" name="email" required placeholder="Email">
                </div>
                <div class="textbox">
                    <input type="password" name="password" required placeholder="Password">
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
