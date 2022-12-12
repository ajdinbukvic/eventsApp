package com.ptf.si.wp.zadaca1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptf.si.wp.zadaca1.models.in.CommentIn;
import com.ptf.si.wp.zadaca1.services.CommentService;
import com.ptf.si.wp.zadaca1.services.EventService;

@Controller
@RequestMapping("/comments")
public class CommentController {

  @Autowired
  private CommentService _commentService;

  @Autowired
  private EventService _eventService;
  
  @PostMapping(value = "/post", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String addNewEvent(@ModelAttribute("commentIn") CommentIn commentIn, Model model) {
    _commentService.postNewComment(commentIn);
    model.addAttribute("success", "Uspješno ste dodali novi komentar!");
    model.addAttribute("userId", commentIn.getUserId());
    model.addAttribute("event", _eventService.getEventById(commentIn.getEventId()));
    model.addAttribute("commentIn", commentIn);
    model.addAttribute("comments", _commentService.getAllCommentsByEventId(commentIn.getEventId()));
    return "event";
  }
}
