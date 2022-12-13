package com.ptf.si.wp.zadaca1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.in.EventIn;
import com.ptf.si.wp.zadaca1.models.out.EventOut;

@Service
public interface EventService {

  public List<EventOut> getAllEvents();

  public List<EventOut> getAllActiveEvents();

  public List<EventOut> getAllEventsByName(String name);
  
  public List<EventOut> getAllEventsByLocationId(Long id);
  
  public List<EventOut> getAllEventsByCategoryId(Long id);

  public EventOut getEventById(Long id);

  public void addNewEvent(EventIn eventIn);
  
  public void updateEvent(EventIn eventIn);

  public void finishEvent(Long id);

}
