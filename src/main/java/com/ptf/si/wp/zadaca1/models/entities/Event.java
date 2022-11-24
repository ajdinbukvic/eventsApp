package com.ptf.si.wp.zadaca1.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
  private Date date;

  @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
  private Boolean finished;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false)
  private Location location;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false)
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Boolean getFinished() {
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

  public Event(@Size(max = 255) String name, @Size(max = 500) String image, @Size(max = 1000) String description,
      Date date, Boolean finished, Location location, Category category) {
    this.name = name;
    this.image = image;
    this.description = description;
    this.date = date;
    this.finished = finished;
    this.location = location;
    this.category = category;
  }

}
