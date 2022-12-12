package com.ptf.si.wp.zadaca1.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.entities.Comment;
import com.ptf.si.wp.zadaca1.models.entities.Event;
import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.CommentIn;
import com.ptf.si.wp.zadaca1.models.out.CommentOut;
import com.ptf.si.wp.zadaca1.repositories.CommentRepository;
import com.ptf.si.wp.zadaca1.repositories.EventRepository;
import com.ptf.si.wp.zadaca1.repositories.UserRepository;
import com.ptf.si.wp.zadaca1.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService  {

  @Autowired
  private CommentRepository _commentRepository;

  @Autowired
  private EventRepository _eventRepository;

  @Autowired
  private UserRepository _userRepository;
  
  @Override
  public List<CommentOut> getAllCommentsByEventId(Long id) {
    List<Comment> comments = _commentRepository.findAllByEventId(id);
    List<CommentOut> commentsOuts = new ArrayList<CommentOut>();
    comments.forEach(c -> commentsOuts.add(new CommentOut(c)));
    //commentsOuts.forEach(e -> e.setDate((e.getDate().split(" ")[0])));
    return commentsOuts;
  }

  @Override
  public void postNewComment(CommentIn commentIn) {
    Comment c;
    try {
      c = new Comment(commentIn);
      Event e = _eventRepository.findById(commentIn.getEventId()).get();
      c.setEvent(e);
      User u = _userRepository.findById(commentIn.getUserId()).get();
      c.setUser(u);
      SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
      String stringDate = sdf2.format(new Date());
      c.setDate(stringDate);
      System.out.println(c.getDate());
      c.setHidden(false);
      _commentRepository.save(c);
    } catch (Exception e) {

    }
    
  }

  @Override
  public void removeComment(Long id) {
    Comment c = null;
    try {
      c = _commentRepository.findById(id).get();
      if (c != null) {
        c.setHidden(true);
        _commentRepository.save(c);
      }
      else throw new IllegalArgumentException("Komentar s tim ID-om ne postoji!");
    } catch (Exception ex) {

    }
    
  }
  
}
