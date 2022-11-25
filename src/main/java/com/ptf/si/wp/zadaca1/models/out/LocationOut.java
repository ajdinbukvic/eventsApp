package com.ptf.si.wp.zadaca1.models.out;

import com.ptf.si.wp.zadaca1.models.entities.Location;

public class LocationOut {
  private Long id;
  private String name;
  private String image;
  private String description;

  public LocationOut() {
  }

  public LocationOut(Location location) {
    this.id = location.getId();
    this.name = location.getName();
    this.image = location.getImage();
    this.description = location.getDescription();
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

}
