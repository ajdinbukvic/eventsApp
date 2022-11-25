package com.ptf.si.wp.zadaca1.models.in;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventIn {

  @Size(max = 255)
  @NotBlank(message = "Naziv ne smije biti prazan")
  private String name;

  @Size(max = 500)
  @NotBlank(message = "Slika ne smije biti prazna")
  private String image;

  @Size(max = 1000)
  @NotBlank(message = "Opis ne smije biti prazan")
  private String description;

  @NotNull
  private Date date;

  @NotNull
  private Long locationId;

  @NotNull
  private Long categoryId;

  public EventIn(String name, String image, String description, Date date, Long locationId, Long categoryId) {
    this.name = name;
    this.image = image;
    this.description = description;
    this.date = date;
    this.locationId = locationId;
    this.categoryId = categoryId;
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

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

}
