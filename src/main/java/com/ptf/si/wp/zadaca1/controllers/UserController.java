package com.ptf.si.wp.zadaca1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

  @PostMapping(value = "/changepassword/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String changePassword(@Valid @ModelAttribute("userUpdateIn") UserUpdateIn userUpdateIn,
      @PathVariable("id") Long id,
      BindingResult result, Model model) {

    User u = _userService.getUserById(id);
    UserOut userOut = new UserOut(u);
    boolean isPasswordEqual = passwordEncoder.matches(userUpdateIn.getCurrentPassword(), u.getPassword());
  
    if (!isPasswordEqual) {
      result.rejectValue("currentPassword", null, "Unesena trenutna lozinka nije ispravna!");
    }
    if (!userUpdateIn.passwordEquals(userUpdateIn.getPassword(), userUpdateIn.getPasswordConfirm())) {
      result.rejectValue("passwordConfirm", null, "Unesena nova lozinka i potvrda lozinke se ne poklapaju!");
    }
    if (result.hasErrors()) {
      model.addAttribute("userUpdateIn", userUpdateIn);
      model.addAttribute("userOut", userOut);
      return "profile";
    }

    _userService.changePassword(u.getId(), userUpdateIn);
    model.addAttribute("success", "Uspješno ste promijenili lozinku svog profila.");
    model.addAttribute("userUpdateIn", userUpdateIn);
    model.addAttribute("userOut", userOut);
    model.addAttribute("userId", u.getId());
    return "profile";
    
  }

  @PostMapping(value = "/changestatus/{id}")
  public String changeStatus(@PathVariable Long id, Model model) {
    boolean status = _userService.changeStatus(id);
    String message = status == false ? "banovali" : "unbanovali";
    model.addAttribute("success", "Uspješno ste " + message + " korisnika (ID: " + id +")");
    model.addAttribute("users", _userService.getAllUsers());
    return "user-manage";
  }
}
