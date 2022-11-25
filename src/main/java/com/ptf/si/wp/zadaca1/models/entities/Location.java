package com.ptf.si.wp.zadaca1.models.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.ptf.si.wp.zadaca1.models.in.LocationIn;

@Entity
@Table(name = "locations")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(max = 255)
  private String name;

  @Column(nullable = false)
  @Size(max = 500)
  private String image;

  @Column(nullable = false)
  @Size(max = 1000)
  private String description;

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private List<Event> events;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  public Location() {
  }

  public Location(LocationIn locationIn) {
    name = locationIn.getName();
    image = locationIn.getImage();
    description = locationIn.getDescription();
  }

}
