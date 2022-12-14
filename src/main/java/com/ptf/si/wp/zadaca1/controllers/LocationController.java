package com.ptf.si.wp.zadaca1.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ptf.si.wp.zadaca1.models.in.LocationIn;
import com.ptf.si.wp.zadaca1.services.LocationService;

@Controller
@RequestMapping("/locations")
public class LocationController {

  @Autowired
  private LocationService _locationService;

  @PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String addNewLocation(@Valid @ModelAttribute("locationIn") LocationIn locationIn, Model model) {
    _locationService.addNewLocation(locationIn);
    model.addAttribute("success", "Uspješno ste dodali novu lokaciju!");
    model.addAttribute("locations", _locationService.getAllLocations());
    return "location-manage";
  }

  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateLocation(@Valid @ModelAttribute("locationIn") LocationIn locationIn, Model model) {
    _locationService.updateLocation(locationIn);
    model.addAttribute("success", "Uspješno ste uredili lokaciju (ID: " + locationIn.getId() + ")");
    model.addAttribute("locations", _locationService.getAllLocations());
    return "location-manage";
  }
}
