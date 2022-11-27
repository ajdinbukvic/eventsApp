package com.ptf.si.wp.zadaca1.services.impl;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.entities.Location;
import com.ptf.si.wp.zadaca1.models.in.LocationIn;
import com.ptf.si.wp.zadaca1.models.out.LocationOut;
import com.ptf.si.wp.zadaca1.repositories.LocationRepository;
import com.ptf.si.wp.zadaca1.services.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

  @Autowired
  private LocationRepository _locationRepository;

  @Override
  public List<LocationOut> getAllLocations() {
    List<Location> locations = _locationRepository.findAll();
    List<LocationOut> locationOuts = new ArrayList<LocationOut>();
    locations.forEach(l -> locationOuts.add(new LocationOut(l)));
    return locationOuts;
  }

  @Override
  public void addNewLocation(LocationIn locationIn) {
    Location l;
    try {
      l = new Location(locationIn);
      _locationRepository.save(l);
    } catch (Exception e) {

    }
  }

  @Override
  public void updateLocation(LocationIn locationIn) {
    Location l = _locationRepository.findById(locationIn.getId()).get();
    try {
      if (l != null) {
        Location updatedLocation = new Location(locationIn);
        _locationRepository.save(updatedLocation);
      } else
        throw new IllegalArgumentException("Lokacija s tim ID-om ne postoji!");
    } catch (Exception e) {

    }
  }

}
