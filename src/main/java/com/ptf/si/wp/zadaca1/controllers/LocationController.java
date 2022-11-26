package com.ptf.si.wp.zadaca1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptf.si.wp.zadaca1.models.in.LocationIn;
import com.ptf.si.wp.zadaca1.models.out.LocationOut;
import com.ptf.si.wp.zadaca1.services.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

  @Autowired
  private LocationService _locationService;

  @GetMapping(value = "/")
  public List<LocationOut> getAllLocations() {
    return _locationService.getAllLocations();
  }

  @PostMapping(value = "/save")
  public LocationOut addNewLocation(@RequestBody LocationIn locationIn) {
    return _locationService.addNewLocation(locationIn);
  }

  @PutMapping(value = "/update/{id}")
  public LocationOut updateCategory(@PathVariable("id") Long id, @RequestBody LocationIn locationIn) {
    return _locationService.updateLocation(id, locationIn);
  }
}
