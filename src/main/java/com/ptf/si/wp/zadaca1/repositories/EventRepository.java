package com.ptf.si.wp.zadaca1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptf.si.wp.zadaca1.models.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  @Query(value = "SELECT * FROM events e WHERE e.finished = 0 AND DATE_FORMAT(STR_TO_DATE(e.date,'%d/%m/%Y'), '%Y-%m-%d') >= CURDATE()", nativeQuery = true)
  public List<Event> findAllActiveEvents();

  // @Modifying
  // @Query("UPDATE Event e SET e.finished = 1 WHERE e.id = ?1")
  // public int changeFinishedStatus(Long id);
}
