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
import com.service.Validators;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SessionController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  Validators validator;

  @Autowired
  BCryptPasswordEncoder encoder;

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

}
