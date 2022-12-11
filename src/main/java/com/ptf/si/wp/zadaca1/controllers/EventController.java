package com.ptf.si.wp.zadaca1.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptf.si.wp.zadaca1.models.in.EventIn;
import com.ptf.si.wp.zadaca1.models.out.EventOut;
import com.ptf.si.wp.zadaca1.services.CategoryService;
import com.ptf.si.wp.zadaca1.services.EventService;
import com.ptf.si.wp.zadaca1.services.LocationService;

@Controller
@RequestMapping("/api/events")
public class EventController {

  @Autowired
  private EventService _eventService;

  @Autowired
  private LocationService _locationService;

  @Autowired
  private CategoryService _categoryService;

  @GetMapping(value = "/")
  public List<EventOut> getAllEvents() {
    return _eventService.getAllEvents();
  }

  @PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String addNewEvent(@ModelAttribute("eventIn") EventIn eventIn, Model model) {
    _eventService.addNewEvent(eventIn);
    model.addAttribute("success", "Uspješno ste dodali novi događaj!");
    model.addAttribute("events", _eventService.getAllEvents());
    model.addAttribute("locations", _locationService.getAllLocations());
    model.addAttribute("categories", _categoryService.getAllCategories());
    return "event-manage";
  }

  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateEvent(@ModelAttribute("eventIn") EventIn eventIn, Model model) {
    //System.out.println(eventIn);
    _eventService.updateEvent(eventIn);
    model.addAttribute("success", "Uspješno ste uredili događaj (ID: " + eventIn.getId() + ")");
    model.addAttribute("events", _eventService.getAllEvents());
    model.addAttribute("locations", _locationService.getAllLocations());
    model.addAttribute("categories", _categoryService.getAllCategories());
    return "event-manage";
  }

}
