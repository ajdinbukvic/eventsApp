package com.ptf.si.wp.zadaca1.models.in;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentIn {

  @Size(max = 1000)
  @NotBlank(message = "Tekst ne smije biti prazan")
  private String text;

  @NotNull
  private Date date;

  @NotNull
  private Long userId;

  @NotNull
  private Long eventId;

  public CommentIn(String text, Date date, Long userId, Long eventId) {
    this.text = text;
    this.date = date;
    this.userId = userId;
    this.eventId = eventId;
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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

}
