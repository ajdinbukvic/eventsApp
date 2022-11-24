package com.ptf.si.wp.zadaca1.models.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

  private Set<Role> roles;

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

  public Boolean getBanned() {
    return banned;
  }

  public void setBanned(Boolean banned) {
    this.banned = banned;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public User() {
  }

  public User(@Size(max = 255) String firstName, @Size(max = 255) String lastName, @Size(max = 255) String email,
      @Size(max = 255) String password, Boolean banned, List<Comment> comments, Set<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.banned = banned;
    this.comments = comments;
    this.roles = roles;
  }

}