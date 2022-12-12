package com.ptf.si.wp.zadaca1.models.in;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentIn {

  private Long id;
  
  @Size(max = 1000)
  @NotBlank(message = "Tekst ne smije biti prazan")
  private String text;

  @NotNull
  private Long userId;

  @NotNull
  private Long eventId;

  public CommentIn() {

  }

  public CommentIn(Long id, String text, Long userId, Long eventId) {
    this.id = id;
    this.text = text;
    this.userId = userId;
    this.eventId = eventId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
