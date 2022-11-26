package com.ptf.si.wp.zadaca1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ptf.si.wp.zadaca1.models.SecurityUser;
import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserIn;
//import com.ptf.si.wp.zadaca1.models.out.UserOut;
import com.ptf.si.wp.zadaca1.services.UserService;

@Controller
public class ViewController {

  @Autowired
  private UserService _userService;

  @GetMapping(value = "/home")
  public String homePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    model.addAttribute("user", user);
    User u = _userService.getUserByEmail(user.getUsername());
    model.addAttribute("id", u.getId());
    return "home";
  }

  @GetMapping(value = "/profile/{id}")
  public String profilePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    UserIn userIn = new UserIn();
    model.addAttribute("userIn", userIn);
    return "profile";
  }

  @GetMapping(value = "/event")
  public String eventPage(Model model, @AuthenticationPrincipal SecurityUser user) {
    // model.addAttribute("user", user);
    // event.findnyid...
    return "event";
  }
}
