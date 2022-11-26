package com.ptf.si.wp.zadaca1.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.SecurityUser;
import com.ptf.si.wp.zadaca1.models.entities.Role;
import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserIn;
import com.ptf.si.wp.zadaca1.models.out.UserOut;
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
      User u = new User(userIn);
      u.setPassword(passwordEncoder.encode(userIn.getPassword()));
      u.setRoles(Arrays.asList(new Role("ROLE_USER")));
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
    users.forEach(u -> usersOuts.add(new UserOut(u)));
    return usersOuts;
  }

  @Override
  public void updatePassword(Long id, UserIn userIn) {
    Optional<User> u = _userRepository.findById(id);
    try {
      if (u != null) {
        User updatedUser = new User(userIn);
        updatedUser.setId(id);
        updatedUser.setPassword(passwordEncoder.encode(userIn.getPassword()));
        _userRepository.save(updatedUser);
      } else
        throw new IllegalArgumentException("Korisnik s tim ID-om ne postoji!");
    } catch (Exception e) {

    }
    // return null;

  }

  @Override
  public User getUserByEmail(String email) {
    User user = _userRepository.findByEmail(email).get();
    // UserOut user = new UserOut();
    // user.setBanned(u.get().isBanned());
    // user.setEmail(u.get().getEmail());
    // user.setFirstName(u.get().getFirstName());
    // user.setLastName(u.get().getLastName());
    // user.setId(u.get().getId());
    // user.setRoles(u.get().getRoles());
    return user;
  }

}
