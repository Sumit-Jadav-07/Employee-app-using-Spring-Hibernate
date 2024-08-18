<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>Forgot Password Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sendotp.css">
  </head>

  <body>
    <div class="otp-container">
      <div class="otp-box">
        <h1>Update Password</h1>
        <form action="forgotpassword" method="post">
          <div class="textbox">
            <input type="text" name="email" placeholder="Email" value="${user.email}">
            <span class="error-details">${emailError}</span>
          </div>
          <div class="textbox">
            <input type="text" name="password" placeholder="New Password" value="${user.password}">
            <span class="error-details">${passwordError}</span>
          </div>
          <div class="textbox">
            <input type="text" name="otp" placeholder="OTP" value="${user.otp}">
            <span class="error-details">${otpError}</span>
          </div>
          <button type="submit" class="btn">Update Password</button>
        </form>
      </div>
    </div>
  </body>

  </html>