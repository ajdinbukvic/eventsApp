package com.ptf.si.wp.zadaca1.models.in;

//import java.sql.Date;
//import java.sql.Date;
//import java.time.LocalDate;

//import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//import org.springframework.format.annotation.DateTimeFormat;

public class EventIn {

  private Long id;

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
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String date;

  @NotNull
  private Long locationId;

  @NotNull
  private Long categoryId;

  public EventIn() {
  }

  public EventIn(Long id, String name, String image, String description, String date, Long locationId,
      Long categoryId) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.description = description;
    this.date = date;
    this.locationId = locationId;
    this.categoryId = categoryId;
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

  @Override
  public String toString() {
    return "EventIn [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", date="
        + date + ", locationId=" + locationId + ", categoryId=" + categoryId + "]";
  }

}
