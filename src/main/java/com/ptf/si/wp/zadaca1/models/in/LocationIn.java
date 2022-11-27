package com.ptf.si.wp.zadaca1.models.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LocationIn {

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

  public LocationIn() {
  }

  public LocationIn(Long id, String name, String image, String description) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.description = description;
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
