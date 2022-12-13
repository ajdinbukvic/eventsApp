package com.ptf.si.wp.zadaca1.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.entities.Role;
import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserIn;
import com.ptf.si.wp.zadaca1.models.in.UserUpdateIn;
import com.ptf.si.wp.zadaca1.models.out.UserOut;
import com.ptf.si.wp.zadaca1.repositories.RoleRepository;
import com.ptf.si.wp.zadaca1.repositories.UserRepository;
import com.ptf.si.wp.zadaca1.security.SecurityUser;
import com.ptf.si.wp.zadaca1.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository _userRepository;

  @Autowired
  private RoleRepository _roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public boolean userExist(String email) {
    Optional<User> u = _userRepository.findByEmail(email);
    return u.isEmpty() == false;
  }

  @Override
  public SecurityUser createProfile(UserIn userIn) {
    try {
      User u = new User(userIn);
      Role r = _roleRepository.findByName("USER");
      u.setPassword(passwordEncoder.encode(userIn.getPassword()));
      u.setRoles(Arrays.asList(r));
      u.setBanned(false);
      _userRepository.save(u);
      return new SecurityUser(u);
    } catch (Exception e) {

    }
    return null;
  }

  @Override
  public List<UserOut> getAllUsers() {
    List<User> users = _userRepository.findAll();
    List<UserOut> usersOuts = new ArrayList<UserOut>();
    users.forEach(u -> {
      if(u.getRoles().size() == 1) usersOuts.add(new UserOut(u));
    });
    return usersOuts;
  }

  @Override
  public void changePassword(Long id, UserUpdateIn userUpdateIn) {
    User u = _userRepository.findById(id).get();
    try {
      if (u != null) {
        u.setPassword(passwordEncoder.encode(userUpdateIn.getPassword()));
        _userRepository.save(u);
      } 
      else throw new IllegalArgumentException("Korisnik s tim ID-om ne postoji!");
    } catch (Exception e) {

    }
  }

  @Override
  public User getUserByEmail(String email) {
    User user = _userRepository.findByEmail(email).get();
    return user;
  }

  @Override
  public User getUserById(Long id) {
    User user = _userRepository.findById(id).get();
    return user;
  }

  @Override
  public boolean changeStatus(Long id) {
    User u = _userRepository.findById(id).get();
    try {
      if (u != null) {
        u.setBanned(!u.isBanned());
        _userRepository.save(u);
      }
      else throw new IllegalArgumentException("Korisnik s tim ID-om ne postoji!");
    } catch (Exception ex) {

    }

    return !u.isBanned(); 
  }

}
