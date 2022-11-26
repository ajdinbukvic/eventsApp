package com.ptf.si.wp.zadaca1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.in.LocationIn;
import com.ptf.si.wp.zadaca1.models.out.LocationOut;

@Service
public interface LocationService {

  public List<LocationOut> getAllLocations();

  public LocationOut addNewLocation(LocationIn locationIn);

  public LocationOut updateLocation(Long id, LocationIn locationIn);
}
