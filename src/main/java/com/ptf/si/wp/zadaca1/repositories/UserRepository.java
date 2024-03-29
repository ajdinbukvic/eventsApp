package com.ptf.si.wp.zadaca1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptf.si.wp.zadaca1.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findByEmail(String email);
  
}
