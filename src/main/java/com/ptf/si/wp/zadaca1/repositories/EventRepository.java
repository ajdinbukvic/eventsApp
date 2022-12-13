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

  @Query(value = "SELECT * FROM events e WHERE e.finished = 0 AND DATE_FORMAT(STR_TO_DATE(e.date,'%d/%m/%Y'), '%Y-%m-%d') >= CURDATE() AND e.name LIKE %?1%", nativeQuery = true)
  public List<Event> findByNameContainingIgnoreCase(String name);

  @Query(value = "SELECT * FROM events e WHERE e.finished = 0 AND DATE_FORMAT(STR_TO_DATE(e.date,'%d/%m/%Y'), '%Y-%m-%d') >= CURDATE() AND e.location_id = ?1", nativeQuery = true)
  public List<Event> findByLocationId(Long id);

  @Query(value = "SELECT * FROM events e WHERE e.finished = 0 AND DATE_FORMAT(STR_TO_DATE(e.date,'%d/%m/%Y'), '%Y-%m-%d') >= CURDATE() AND e.category_id = ?1", nativeQuery = true)
  public List<Event> findByCategoryId(Long id);
  
}
