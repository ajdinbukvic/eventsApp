package com.ptf.si.wp.zadaca1.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.entities.Category;
import com.ptf.si.wp.zadaca1.models.entities.Event;
import com.ptf.si.wp.zadaca1.models.entities.Location;
import com.ptf.si.wp.zadaca1.models.in.EventIn;
import com.ptf.si.wp.zadaca1.models.out.EventOut;
import com.ptf.si.wp.zadaca1.repositories.CategoryRepository;
import com.ptf.si.wp.zadaca1.repositories.EventRepository;
import com.ptf.si.wp.zadaca1.repositories.LocationRepository;
import com.ptf.si.wp.zadaca1.services.EventService;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository _eventRepository;

  @Autowired
  private CategoryRepository _categoryRepository;

  @Autowired
  private LocationRepository _locationRepository;

  @Override
  public List<EventOut> getAllEvents() {
    List<Event> events = _eventRepository.findAll();
    List<EventOut> eventsOuts = new ArrayList<EventOut>();
    events.forEach(e -> eventsOuts.add(new EventOut(e)));
    return eventsOuts;
  }

  @Override
  public void addNewEvent(EventIn eventIn) {
    Event e;
    try {
      e = new Event(eventIn);
      Category c = _categoryRepository.findById(eventIn.getCategoryId()).get();
      e.setCategory(c);
      Location l = _locationRepository.findById(eventIn.getLocationId()).get();
      e.setLocation(l);
      e.setFinished(false);
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
      String stringDate = sdf2.format(sdf1.parse(eventIn.getDate()));
      e.setDate(stringDate);
      _eventRepository.save(e);
    } catch (Exception ex) {

    }
  }

  @Override
  public void updateEvent(EventIn eventIn) {
    Event e = _eventRepository.findById(eventIn.getId()).get();
    try {
      if (e != null) {
        Event updatedEvent = new Event(eventIn);
        Category c = _categoryRepository.findById(eventIn.getCategoryId()).get();
        updatedEvent.setCategory(c);
        Location l = _locationRepository.findById(eventIn.getLocationId()).get();
        updatedEvent.setLocation(l);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = sdf2.format(sdf1.parse(eventIn.getDate()));
        updatedEvent.setDate(stringDate);
        if(!updatedEvent.getDate().equals(e.getDate())) updatedEvent.setFinished(false);
        else updatedEvent.setFinished(e.isFinished());
        _eventRepository.save(updatedEvent);
      } else
        throw new IllegalArgumentException("Event s tim ID-om ne postoji!");
    } catch (Exception ex) {

    }

  }

  @Override
  public List<EventOut> getAllActiveEvents() {
    List<Event> events = _eventRepository.findAllActiveEvents();
    List<EventOut> eventsOuts = new ArrayList<EventOut>();
    events.forEach(e -> eventsOuts.add(new EventOut(e)));
    return eventsOuts;
  }

  @Override
  public EventOut getEventById(Long id) {
    Event e = _eventRepository.findById(id).get();
    return new EventOut(e);
  }

  @Override
  public void finishEvent(Long id) {
    try {
      Event e = _eventRepository.findById(id).get();
      if (e != null) {
        e.setFinished(true);
        _eventRepository.save(e);
      }
      else throw new IllegalArgumentException("Event s tim ID-om ne postoji!");
    } catch (Exception ex) {

    }

    
  }

  @Override
  public List<EventOut> getAllEventsByName(String name) {
    List<Event> events = _eventRepository.findByNameContainingIgnoreCase(name);
    List<EventOut> eventsOuts = new ArrayList<EventOut>();
    events.forEach(e -> eventsOuts.add(new EventOut(e)));
    return eventsOuts;
  }

  @Override
  public List<EventOut> getAllEventsByLocationId(Long id) {
    List<Event> events = _eventRepository.findByLocationId(id);
    List<EventOut> eventsOuts = new ArrayList<EventOut>();
    events.forEach(e -> eventsOuts.add(new EventOut(e)));
    return eventsOuts;
  }

  @Override
  public List<EventOut> getAllEventsByCategoryId(Long id) {
    List<Event> events = _eventRepository.findByCategoryId(id);
    List<EventOut> eventsOuts = new ArrayList<EventOut>();
    events.forEach(e -> eventsOuts.add(new EventOut(e)));
    return eventsOuts;
  }

}
