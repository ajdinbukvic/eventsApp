package com.ptf.si.wp.zadaca1.models.out;

import java.util.Collection;

import com.ptf.si.wp.zadaca1.models.entities.Role;
import com.ptf.si.wp.zadaca1.models.entities.User;

public class UserOut {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Boolean banned;
  private Collection<Role> roles;

  public UserOut() {
  }

  public UserOut(User user) {
    this.id = user.getId();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
    this.banned = user.isBanned();
    this.roles = user.getRoles();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getBanned() {
    return banned;
  }

  public void setBanned(Boolean banned) {
    this.banned = banned;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }

}
