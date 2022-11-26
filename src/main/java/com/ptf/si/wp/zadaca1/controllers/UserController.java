package com.ptf.si.wp.zadaca1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserIn;
import com.ptf.si.wp.zadaca1.models.out.UserOut;
import com.ptf.si.wp.zadaca1.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService _userService;

  @GetMapping(value = "/")
  public List<UserOut> getAllUsers() {
    return _userService.getAllUsers();
  }

  // @PutMapping(value = "/updatePassword/{id}")
  // public UserOut updatePassword(@PathVariable("id") Long id, @RequestBody
  // UserIn userIn) {
  // return _userService.updatePassword(id, userIn);
  // }
  @PostMapping(value = "/updatePassword/{id}")
  public String updatePassword(@Valid @ModelAttribute("user") UserIn userIn, BindingResult result, Model model,
      RedirectAttributes ra) {

    User u = _userService.getUserByEmail(userIn.getEmail());
    if (!u.getPassword().equals(userIn.getCurrentPassword())) {
      result.rejectValue("currentPassword", null, "Unesena trenutna lozinka nije ispravna!");
    }
    if (!userIn.passwordEquals(userIn.getPassword(), userIn.getPasswordConfirm())) {
      result.rejectValue("passwordConfirm", null, "Unesena lozinka i potvrda lozinke se ne poklapaju!");
    }
    if (result.hasErrors()) {
      model.addAttribute("user", userIn);
      // return "redirect:profile/${u.getId()}";
      return "redirect:home";
    }
    _userService.updatePassword(u.getId(), userIn);
    ra.addFlashAttribute("success", "Uspješno ste promijenili lozinku svog profila.");
    // return "redirect:profile/${u.getId()}";
    return "redirect:home";
  }
}
