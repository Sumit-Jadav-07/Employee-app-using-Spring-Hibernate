<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>Send OTP Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sendotp.css">
  </head>

  <body>
    <div class="otp-container">
      <div class="otp-box">
        <h1>Send OTP</h1>
        <form action="sendotp" method="post">
          <div class="textbox">
            <input type="text" name="email" placeholder="Email" value="${email}">
            <span class="error-details">${emailError}</span>
          </div>
          <button type="submit" class="btn">Send OTP</button>
        </form>
        <div class="goback-link">
          <button onclick="goBack()" class="back-button">Go Back</button>
        </div>
      </div>
    </div>

    <script>
      function goBack() {
        window.history.back();
      }
    </script>
  </body>

  </html>