<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/errorpage.css">
</head>
<body>
    <div class="error-container">
        <div class="error-box">
            <div class="error-icon">
                <div class="icon">
                    <span class="cross"></span>
                </div>
            </div>
            <h1 class="error-title">Oops! Something Went Wrong</h1>
            <p class="error-message">${errorMessage}</p>
            <a href="${pageContext.request.contextPath}/" class="home-button">Go to Homepage</a>
            <button onclick="goBack()" class="back-button">Go Back</button>
        </div>
    </div>
    
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
