package com.ptf.si.wp.zadaca1.services.impl;

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
    eventsOuts.forEach(e -> e.setDate(e.getDate().split(" ")[0]));
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
        System.out.println(c.getName());
        updatedEvent.setCategory(c);
        Location l = _locationRepository.findById(eventIn.getLocationId()).get();
        updatedEvent.setLocation(l);
        updatedEvent.setFinished(false);
        _eventRepository.save(updatedEvent);
      } else
        throw new IllegalArgumentException("Lokacija s tim ID-om ne postoji!");
    } catch (Exception ex) {

    }

  }

}
