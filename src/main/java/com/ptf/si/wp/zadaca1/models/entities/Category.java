package com.ptf.si.wp.zadaca1.models.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(max = 255)
  private String name;

  @Column(nullable = false)
  @Size(max = 500)
  private String icon;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  public Category() {
  }

  public Category(@Size(max = 255) String name, @Size(max = 500) String icon, List<Event> events) {
    this.name = name;
    this.icon = icon;
    this.events = events;
  }

}
