package com.ptf.si.wp.zadaca1.services;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.SecurityUser;
import com.ptf.si.wp.zadaca1.models.entities.User;
import com.ptf.si.wp.zadaca1.models.in.UserIn;
import com.ptf.si.wp.zadaca1.models.in.UserUpdateIn;
import com.ptf.si.wp.zadaca1.models.out.UserOut;

public interface UserService {

  public boolean userExist(String email);

  public SecurityUser createProfile(UserIn userIn);

  public List<UserOut> getAllUsers();

  public void updatePassword(Long id, UserUpdateIn userUpdateIn);

  public boolean updateUserStatus(Long id);

  public User getUserByEmail(String email);

  public User getUserById(Long id);

}
