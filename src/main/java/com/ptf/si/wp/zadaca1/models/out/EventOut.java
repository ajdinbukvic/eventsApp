package com.ptf.si.wp.zadaca1.models.out;

import java.util.Date;

import com.ptf.si.wp.zadaca1.models.entities.Event;

public class EventOut {
  private Long id;
  private String name;
  private String image;
  private String description;
  private Date date;
  private Boolean finished;
  private Long categoryId;
  private Long locationId;

  public EventOut() {
  }

  public EventOut(Event event) {
    this.id = event.getId();
    this.name = event.getName();
    this.image = event.getImage();
    this.description = event.getDescription();
    this.date = event.getDate();
    this.finished = event.isFinished();
    this.categoryId = event.getCategory().getId();
    this.locationId = event.getLocation().getId();
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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

}
