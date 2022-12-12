package com.ptf.si.wp.zadaca1.models.out;

import com.ptf.si.wp.zadaca1.models.entities.Comment;

public class CommentOut {
  private Long id;
  private String text;
  private String date;
  private Boolean hidden;
  private Long userId;
  private String userFirstName;
  private String userLastName;
  private Long eventId;

  public CommentOut() {
  }

  public CommentOut(Comment comment) {
    this.id = comment.getId();
    this.text = comment.getText();
    this.date = comment.getDate();
    this.hidden = comment.isHidden();
    this.userId = comment.getUser().getId();
    this.userFirstName = comment.getUser().getFirstName();
    this.userLastName = comment.getUser().getLastName();
    this.eventId = comment.getEvent().getId();
  }

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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserFirstName() {
    return userFirstName;
  }

  public void setUserFirstName(String userFirstName) {
    this.userFirstName = userFirstName;
  }

  public String getUserLastName() {
    return userLastName;
  }

  public void setUserLastName(String userLastName) {
    this.userLastName = userLastName;
  }

  public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

}
