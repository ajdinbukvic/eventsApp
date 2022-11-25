package com.ptf.si.wp.zadaca1.services.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.SecurityUser;
import com.ptf.si.wp.zadaca1.models.entities.Role;
import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserIn;
import com.ptf.si.wp.zadaca1.repositories.UserRepository;
import com.ptf.si.wp.zadaca1.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository _userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository _userRepository, PasswordEncoder passwordEncoder) {
    super();
    this._userRepository = _userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public boolean userExist(String email) {
    Optional<User> u = _userRepository.findByEmail(email);
    return u.isEmpty() == false;
  }

  @Override
  public SecurityUser createProfile(UserIn userIn) {
    try {

      // if (userExist(userIn.getEmail()))
      // throw new IllegalArgumentException("Vec postoji korisnik s tim email-om!");

      // if (!userIn.passwordEquals(userIn.getPassword(),
      // userIn.getPasswordConfirm()))
      // throw new IllegalArgumentException("Unesena lozinka i potvrda lozinke se ne
      // poklapaju!");

      User u = new User(userIn);
      u.setPassword(passwordEncoder.encode(userIn.getPassword()));
      u.setRoles(Arrays.asList(new Role("ROLE_USER")));
      u.setBanned(false);
      _userRepository.save(u);
      return new SecurityUser(u);
      // return new SecurityUser(_userRepository.save(u));
    } catch (Exception e) {

    }
    return null;
  }

}
