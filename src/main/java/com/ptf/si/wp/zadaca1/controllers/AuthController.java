package com.ptf.si.wp.zadaca1.controllers;

import javax.validation.Valid;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;

import com.ptf.si.wp.zadaca1.models.in.UserIn;
import com.ptf.si.wp.zadaca1.services.UserService;

@Controller
public class AuthController {

  // @Autowired(required = false)
  private final UserService _userService;

  public AuthController(UserService _userService) {
    super();
    this._userService = _userService;
  }

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @GetMapping(value = "/register")
  public String registerPage(Model model) {
    UserIn userIn = new UserIn();
    model.addAttribute("user", userIn);
    return "register";
  }

  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)

  public String createProfile(@Valid @ModelAttribute("user") UserIn userIn, BindingResult result, Model model) {
    // public String createProfile(@Valid @RequestBody UserIn userIn, BindingResult
    // result, Model model) {
    // System.out.println(userInnn);
    // UserIn userIn = new UserIn("testt", "testttt", "ajdin@gmail.com", "1234",
    // "1234");
    boolean userEmailExists = _userService.userExist(userIn.getEmail());
    if (userEmailExists) {
      result.rejectValue("email", null, "Vec postoji korisnik s tim email-om!");
    }
    if (!userIn.passwordEquals(userIn.getPassword(), userIn.getPasswordConfirm())) {
      result.rejectValue("passwordConfirm", null, "Unesena lozinka i potvrda lozinke se ne poklapaju!");
    }
    if (result.hasErrors()) {
      model.addAttribute("user", userIn);
    }
    _userService.createProfile(userIn);
    return "redirect:login";
  }

}
