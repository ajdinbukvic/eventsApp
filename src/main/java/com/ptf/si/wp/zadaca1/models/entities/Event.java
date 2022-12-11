package com.ptf.si.wp.zadaca1.models.entities;

//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

//import org.springframework.format.annotation.DateTimeFormat;

import com.ptf.si.wp.zadaca1.models.in.EventIn;

@Entity
@Table(name = "events")
public class Event {

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

  @Column(nullable = false)
  @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
  private String date;

  @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
  private Boolean finished;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  private Location location;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
  private List<Comment> comments;

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Boolean isFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Event() {
  }

  public Event(EventIn eventIn) {
    id = eventIn.getId();
    name = eventIn.getName();
    image = eventIn.getImage();
    description = eventIn.getDescription();
    date = eventIn.getDate();
  }

}
