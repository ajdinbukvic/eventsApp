package com.ptf.si.wp.zadaca1.services;

import org.springframework.stereotype.Service;

import com.ptf.si.wp.zadaca1.models.SecurityUser;
import com.ptf.si.wp.zadaca1.models.in.UserIn;

@Service
public interface UserService {

  boolean userExist(String email);

  SecurityUser createProfile(UserIn userIn);

}
