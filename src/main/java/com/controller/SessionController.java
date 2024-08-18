package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.entity.UserEntity;
import com.repository.UserRepository;
import com.service.OtpService;
import com.service.Validators;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  Validators validator;

  @Autowired
  BCryptPasswordEncoder encoder;

  @Autowired
  OtpService otpService;

  // @Autowired
  // JavaMailSender sender;

  @GetMapping("/signuppage")
  public String signUpPage() {
    return "Signup";
  }

  @PostMapping("/signup")
  public String signUp(UserEntity user, Model model) {
    String fullnameError = validator.fullNameValidation(user.getName(), model);
    String emailError = validator.emailValidation(user.getEmail(), model);
    String passwordError = validator.passwordValidation(user.getPassword(), model);

    model.addAttribute("user", user);

    if (!fullnameError.isEmpty() || !emailError.isEmpty() || !passwordError.isEmpty()) {
      return "Signup";
    } else {
      user.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(user);
      return "redirect:/loginpage";
    }
  }

  @GetMapping("/loginpage")
  public String loginPage() {
    return "Login";
  }

  @PostMapping("/login")
  public String login(
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      HttpServletResponse response,
      Model model) {
    boolean authStatus = true;
    UserEntity user = null;

    if (email.isEmpty()) {
      model.addAttribute("emailError", "Email can't be empty");
      authStatus = false;
    }
    if (password.isEmpty()) {
      model.addAttribute("passwordError", "Password can't be empty");
      authStatus = false;
    }

    if (authStatus) {
      user = userRepository.findByEmail(email);
      if (user == null) {
        model.addAttribute("emailError", "Email doesn't exist");
        authStatus = false;
      } else if (!encoder.matches(password, user.getPassword())) {
        model.addAttribute("passwordError", "Password doesn't match");
        authStatus = false;
      }
    }

    model.addAttribute("email", email);
    model.addAttribute("password", password);

    if (authStatus) {
      @SuppressWarnings("null")
      Cookie cookie = new Cookie("user", user.getUserId().toString());
      cookie.setPath("/");
      cookie.setHttpOnly(true);
      cookie.setMaxAge(15 * 24 * 60 * 60);
      response.addCookie(cookie);
      return "Dashboard";
    } else {
      return "Login";
    }
  }

  @GetMapping("/logout")
  public String logout(HttpServletResponse response) {
    Cookie cookie = new Cookie("user", "");
    cookie.setPath("/");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    return "redirect:/loginpage";
  }

  @GetMapping("/sendotppage")
  public String sendOtpPage() {
    return "SendOtp";
  }

  @PostMapping("/sendotp")
  public String sendOtp(@RequestParam("email") String email, Model model, HttpSession session) {
    UserEntity user = userRepository.findByEmail(email);
    if (user != null) {
      model.addAttribute("email", email);
      String otp = otpService.getOtp();
      // SimpleMailMessage message = new SimpleMailMessage();
      // message.setFrom("noreplyonthisemail7@gmail.com");
      // message.setTo(email);
      // message.setSubject("Your One Time Password");
      // message.setText("Your OTP Code is : " + otp);
      session.setAttribute("otp", otp);
      return "redirect:/forgotpassword";
    } else {
      model.addAttribute("emailError", "Email doesn't exist");
      return "SendOtp";
    }
  }

  @PostMapping("/forgotpassword")
  public String forgotPassword(@RequestParam("email") String email,
      @RequestParam("password") String password,
      @RequestParam("otp") String otp, Model model, HttpSession session) {
    String emailError = validator.emailValidation(email, model);
    String passwordError = validator.passwordValidation(password, model);
    String oldOtp = (String)session.getAttribute("otp");
    String otpError = validator.otpValidation(oldOtp, otp, model);
    
    UserEntity user = userRepository.findByEmail(email);
    model.addAttribute("user", user);

    if (!emailError.isEmpty() || !passwordError.isEmpty() || !otpError.isEmpty()) {
      return "ForgotPassword";
    } else {
      user.setPassword(encoder.encode(password));
      userRepository.save(user);
      return "redirect:/loginpage";
    }
  }
  


}
