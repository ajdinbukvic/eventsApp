package com.ptf.si.wp.zadaca1.models.entities;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.ptf.si.wp.zadaca1.models.in.CommentIn;

@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(max = 1000)
  private String text;

  @Column(nullable = false)
  private Date date;

  @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
  private Boolean hidden;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false)
  private Event event;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Boolean isHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Comment() {
  }

  public Comment(CommentIn commentIn) {
    text = commentIn.getText();
    date = commentIn.getDate();
  }

}
