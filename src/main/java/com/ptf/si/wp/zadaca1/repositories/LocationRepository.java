package com.ptf.si.wp.zadaca1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptf.si.wp.zadaca1.models.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
