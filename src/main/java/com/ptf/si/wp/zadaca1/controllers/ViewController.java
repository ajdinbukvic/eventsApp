package com.ptf.si.wp.zadaca1.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ptf.si.wp.zadaca1.models.SecurityUser;

@Controller
public class ViewController {

  @GetMapping(value = "/home")
  public String homePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    model.addAttribute("user", user);
    return "home";
  }

  @GetMapping(value = "/profile")
  public String profilePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    // model.addAttribute("user", user);
    return "profile";
  }
}
