package com.ptf.si.wp.zadaca1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.in.CommentIn;
import com.ptf.si.wp.zadaca1.models.out.CommentOut;

@Service
public interface CommentService {
  
  public List<CommentOut> getAllCommentsByEventId(Long id);

  public void postNewComment(CommentIn commentIn);

  public void removeComment(Long id);
}
