package com.ptf.si.wp.zadaca1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptf.si.wp.zadaca1.models.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  @Query(value = "SELECT * FROM events e WHERE e.finished = 0 AND e.date >= CURDATE()", nativeQuery = true)
  public List<Event> findAllActiveEvents();
}
