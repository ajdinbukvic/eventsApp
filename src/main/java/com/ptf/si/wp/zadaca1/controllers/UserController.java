package com.ptf.si.wp.zadaca1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserUpdateIn;
import com.ptf.si.wp.zadaca1.models.out.UserOut;
import com.ptf.si.wp.zadaca1.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService _userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping(value = "/")
  public List<UserOut> getAllUsers() {
    return _userService.getAllUsers();
  }

  // @PutMapping(value = "/updatePassword/{id}")
  // public UserOut updatePassword(@PathVariable("id") Long id, @RequestBody
  // userUpdateIn userUpdateIn) {
  // return _userService.updatePassword(id, userUpdateIn);
  // }
  @PostMapping(value = "/updatePassword/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updatePassword(@Valid @ModelAttribute("userUpdateIn") UserUpdateIn userUpdateIn,
      @PathVariable("id") Long id,
      BindingResult result, Model model) {

    User u = _userService.getUserById(id);
    UserOut userOut = new UserOut(u);
    boolean isPasswordEqual = passwordEncoder.matches(userUpdateIn.getCurrentPassword(), u.getPassword());
    // System.out.println(isPasswordEqual);
    if (!isPasswordEqual) {
      result.rejectValue("currentPassword", null, "Unesena trenutna lozinka nije ispravna!");
    }
    if (!userUpdateIn.passwordEquals(userUpdateIn.getPassword(), userUpdateIn.getPasswordConfirm())) {
      result.rejectValue("passwordConfirm", null, "Unesena lozinka i potvrda lozinke se ne poklapaju!");
    }
    if (result.hasErrors()) {
      model.addAttribute("userUpdateIn", userUpdateIn);
      model.addAttribute("userOut", userOut);
      return "profile";
      // return "redirect:home";
    }
    _userService.updatePassword(u.getId(), userUpdateIn);
    model.addAttribute("success", "Uspješno ste promijenili lozinku svog profila.");
    model.addAttribute("userUpdateIn", userUpdateIn);
    model.addAttribute("userOut", userOut);
    return "profile";
    // return "redirect:home";
  }

  @PostMapping(value = "/update/{id}")
  public String updateUserStatus(@PathVariable Long id, Model model) {
    boolean status = _userService.updateUserStatus(id);
    String message = status == false ? "banovali" : "unbanovali";
    model.addAttribute("success", "Uspješno ste " + message + " korisnika (ID: " + id +")");
    model.addAttribute("users", _userService.getAllUsers());
    return "user-manage";
  }
}
