package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.UserEntity;
import com.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/listuser")
  public String listUser(Model model) {
    List<UserEntity> users = userRepository.findAll();
    model.addAttribute("users", users);
    return "ListUser";
  }

  @GetMapping("/viewuser")
  public String viewUser(@RequestParam("userId") Integer id, Model model) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      model.addAttribute("user", user.get());
      return "ViewUser";
    } else {
      model.addAttribute("errorMessage", "User not found");
      return "ErrorPage";
    }
  }

  @GetMapping("/deleteuser")
  public String deleteUser(@RequestParam("userId") Integer id) {
    userRepository.deleteById(id);
    return "redirect:/listuser";
  }

  @GetMapping("/edituserpage")
  public String editUserPage(@RequestParam("userId") Integer id, Model model) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      model.addAttribute("user", user.get());
      return "EditUser";
    } else {
      model.addAttribute("errorMessage", "User not found");
      return "ErrorPage";
    }
  }

  @PostMapping("/edituser")
  public String editUser(@RequestParam("userId") Integer id,
      @RequestParam("name") String name,
      @RequestParam("email") String email, Model model) {
    Optional<UserEntity> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      UserEntity user = optionalUser.get();
      user.setName(name);
      user.setEmail(email);
      userRepository.save(user);
      return "redirect:/listuser";
    } else {
      model.addAttribute("errorMessage", "Updation failed");
      return "ErrorPage";
    }
  }
}