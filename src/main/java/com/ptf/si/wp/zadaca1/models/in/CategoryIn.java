package com.ptf.si.wp.zadaca1.models.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryIn {

  private Long id;

  @Size(max = 255)
  @NotBlank(message = "Naziv ne smije biti prazan")
  private String name;

  @Size(max = 500)
  @NotBlank(message = "Ikona ne smije biti prazna")
  private String icon;

  public CategoryIn() {
  }

  public CategoryIn(Long id, String name, String icon) {
    this.id = id;
    this.name = name;
    this.icon = icon;
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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

}
