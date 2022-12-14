package com.ptf.si.wp.zadaca1.controllers;


import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptf.si.wp.zadaca1.models.in.EventIn;
import com.ptf.si.wp.zadaca1.services.CategoryService;
import com.ptf.si.wp.zadaca1.services.EventService;
import com.ptf.si.wp.zadaca1.services.LocationService;

@Controller
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventService _eventService;

  @Autowired
  private LocationService _locationService;

  @Autowired
  private CategoryService _categoryService;

  @PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String addNewEvent(@Valid @ModelAttribute("eventIn") EventIn eventIn, Model model) {
    _eventService.addNewEvent(eventIn);
    model.addAttribute("success", "Uspješno ste dodali novi događaj!");
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
    return "event-manage";
  }

  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateEvent(@Valid @ModelAttribute("eventIn") EventIn eventIn, Model model) {
    _eventService.updateEvent(eventIn);
    model.addAttribute("success", "Uspješno ste uredili događaj (ID: " + eventIn.getId() + ")");
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
    return "event-manage";
  }

  @PostMapping(value = "/finish/{id}")
  public String finishEvent(@PathVariable Long id, Model model) {
    _eventService.finishEvent(id);
    model.addAttribute("success", "Uspješno ste završili događaj (ID: " + id + ")");
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
    return "event-manage";
  }

}
