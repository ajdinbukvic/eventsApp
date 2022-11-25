package com.ptf.si.wp.zadaca1.models.out;

import com.ptf.si.wp.zadaca1.models.entities.Category;

public class CategoryOut {
  private Long id;
  private String name;
  private String icon;

  public CategoryOut() {
  }

  public CategoryOut(Category category) {
    this.id = category.getId();
    this.name = category.getName();
    this.icon = category.getIcon();
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
