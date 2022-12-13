package com.ptf.si.wp.zadaca1.controllers;


import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ptf.si.wp.zadaca1.models.SecurityUser;
import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.CategoryIn;
import com.ptf.si.wp.zadaca1.models.in.CommentIn;
import com.ptf.si.wp.zadaca1.models.in.EventIn;
import com.ptf.si.wp.zadaca1.models.in.LocationIn;
import com.ptf.si.wp.zadaca1.models.in.UserUpdateIn;
import com.ptf.si.wp.zadaca1.models.out.UserOut;
import com.ptf.si.wp.zadaca1.services.CategoryService;
import com.ptf.si.wp.zadaca1.services.CommentService;
import com.ptf.si.wp.zadaca1.services.EventService;
import com.ptf.si.wp.zadaca1.services.LocationService;
import com.ptf.si.wp.zadaca1.services.UserService;

@Controller
public class ViewController {

  @Autowired
  private UserService _userService;

  @Autowired
  private LocationService _locationService;

  @Autowired
  private CategoryService _categoryService;

  @Autowired
  private EventService _eventService;

  @Autowired
  private CommentService _commentService;

  @GetMapping(value = "/home")
  public String homePage(Model model, @AuthenticationPrincipal SecurityUser user, @Param("naziv") String naziv, @Param("lokacija") Long lokacija, @Param("kategorija") Long kategorija) {
    if (user != null) {
      model.addAttribute("user", user);
      User u = _userService.getUserByEmail(user.getUsername());
      model.addAttribute("userId", u.getId());
    }
    if (naziv != null) model.addAttribute("events", _eventService.getAllEventsByName(naziv));
    
    else if (lokacija != null) model.addAttribute("events", _eventService.getAllEventsByLocationId(lokacija));

    else if (kategorija != null) model.addAttribute("events", _eventService.getAllEventsByCategoryId(kategorija));

    else model.addAttribute("events", _eventService.getAllActiveEvents());

    model.addAttribute("locations", _locationService.getAllLocations());
    model.addAttribute("categories", _categoryService.getAllCategories());
    return "home";
  }

  @GetMapping(value = "/profile/{id}")
  public String profilePage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal SecurityUser user) {
    UserUpdateIn userUpdateIn = new UserUpdateIn();
    model.addAttribute("userUpdateIn", userUpdateIn);
    User u = _userService.getUserByEmail(user.getUsername());
    UserOut userOut = new UserOut(u);
    model.addAttribute("userOut", userOut);
    model.addAttribute("userId", u.getId());
    return "profile";
  }

  @GetMapping(value = "/event/{id}")
  public String eventPage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal SecurityUser user) {
    if (user != null) {
      model.addAttribute("user", user);
      User u = _userService.getUserByEmail(user.getUsername());
      model.addAttribute("userId", u.getId());
    }
    model.addAttribute("event", _eventService.getEventById(id));
    CommentIn commentIn = new CommentIn();
    model.addAttribute("commentIn", commentIn);
    model.addAttribute("comments", _commentService.getAllCommentsByEventId(id));
    return "event";
  }

  @GetMapping(value = "/location-manage")
  public String locationManagePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    if (user != null) {
      model.addAttribute("user", user);
      User u = _userService.getUserByEmail(user.getUsername());
      model.addAttribute("userId", u.getId());
      LocationIn locationIn = new LocationIn();
      model.addAttribute("locationIn", locationIn);
      model.addAttribute("locations", _locationService.getAllLocations());
    }
    return "location-manage";
  }

  @GetMapping(value = "/category-manage")
  public String categoryManagePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    if (user != null) {
      model.addAttribute("user", user);
      User u = _userService.getUserByEmail(user.getUsername());
      model.addAttribute("userId", u.getId());
      CategoryIn categoryIn = new CategoryIn();
      model.addAttribute("categoryIn", categoryIn);
      model.addAttribute("categories", _categoryService.getAllCategories());
    }
    return "category-manage";
  }

  @GetMapping(value = "/user-manage")
  public String userManagePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    if (user != null) {
      model.addAttribute("user", user);
      User u = _userService.getUserByEmail(user.getUsername());
      model.addAttribute("userId", u.getId());
      model.addAttribute("users", _userService.getAllUsers());
    }
    return "user-manage";
  }

  @GetMapping(value = "/event-manage")
  public String eventManagePage(Model model, @AuthenticationPrincipal SecurityUser user) {
    if (user != null) {
      model.addAttribute("user", user);
      User u = _userService.getUserByEmail(user.getUsername());
      model.addAttribute("userId", u.getId());
      EventIn eventIn = new EventIn();
      model.addAttribute("eventIn", eventIn);
      model.addAttribute("events", _eventService.getAllEvents());
      model.addAttribute("locations", _locationService.getAllLocations());
      model.addAttribute("categories", _categoryService.getAllCategories());
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
      String dateStr = null;
      try {
        dateStr = sdf2.format(sdf1.parse(LocalDate.now().toString()));
      } 
      catch(Exception ex) {

      }
      model.addAttribute("currentDate", dateStr);
    }
    return "event-manage";
  }
}
