package com.ptf.si.wp.zadaca1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.repositories.UserRepository;
import com.ptf.si.wp.zadaca1.security.SecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository _userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return _userRepository.findByEmail(username).map(SecurityUser::new)
    .orElseThrow(() -> new UsernameNotFoundException("Korisnik s tim email-om ne postoji!"));
  }
}


