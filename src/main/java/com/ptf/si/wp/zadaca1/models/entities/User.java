package com.ptf.si.wp.zadaca1.models.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.ptf.si.wp.zadaca1.models.in.UserIn;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(max = 255)
  private String firstName;

  @Column(nullable = false)
  @Size(max = 255)
  private String lastName;

  @Column(nullable = false)
  @Size(max = 255)
  private String email;

  @Column(nullable = false)
  @Size(max = 255)
  private String password;

  @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
  private Boolean banned;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Comment> comments;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))

  private Collection<Role> roles;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean isBanned() {
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

  public User() {
  }

  public User(UserIn userIn) {
    firstName = userIn.getFirstName();
    lastName = userIn.getLastName();
    email = userIn.getEmail();
    password = userIn.getPassword();
  }

}
