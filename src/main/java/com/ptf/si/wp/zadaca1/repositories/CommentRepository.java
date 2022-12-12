package com.ptf.si.wp.zadaca1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptf.si.wp.zadaca1.models.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query(value = "SELECT * FROM comments c WHERE c.hidden = 0 AND c.event_id = :id", nativeQuery = true)
  public List<Comment> findAllByEventId(Long id);
 
}
