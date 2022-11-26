package com.ptf.si.wp.zadaca1.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.SecurityUser;
import com.ptf.si.wp.zadaca1.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository _userRepository;

  public CustomUserDetailsService(UserRepository _userRepository) {
    super();
    this._userRepository = _userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return _userRepository.findByEmail(username).map(SecurityUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("Korisnik s tim email-om ne postoji!"));
  }

}
