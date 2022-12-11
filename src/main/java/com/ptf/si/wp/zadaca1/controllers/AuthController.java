package com.ptf.si.wp.zadaca1.controllers;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String createProfile(@Valid @ModelAttribute("user") UserIn userIn, BindingResult result, Model model,
      RedirectAttributes ra) {

    boolean userEmailExists = _userService.userExist(userIn.getEmail());
    if (userEmailExists) {
      result.rejectValue("email", null, "Već postoji korisnik s tim email-om!");
    }
    if (!userIn.passwordEquals(userIn.getPassword(), userIn.getPasswordConfirm())) {
      result.rejectValue("passwordConfirm", null, "Unesena lozinka i potvrda lozinke se ne poklapaju!");
    }
    if (result.hasErrors()) {
      model.addAttribute("user", userIn);
      return "register";
    }
    _userService.createProfile(userIn);
    ra.addFlashAttribute("success", "Uspješno ste se registrovali. Prijavite se u aplikaciju.");
    return "redirect:login";
  }

}
