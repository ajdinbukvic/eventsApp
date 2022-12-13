package com.ptf.si.wp.zadaca1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserIn;
import com.ptf.si.wp.zadaca1.models.in.UserUpdateIn;
import com.ptf.si.wp.zadaca1.models.out.UserOut;
import com.ptf.si.wp.zadaca1.security.SecurityUser;

@Service
public interface UserService {

  public List<UserOut> getAllUsers();
  
  public User getUserByEmail(String email);

  public User getUserById(Long id);

  public boolean userExist(String email);

  public SecurityUser createProfile(UserIn userIn);

  public void changePassword(Long id, UserUpdateIn userUpdateIn);

  public boolean changeStatus(Long id);

}
