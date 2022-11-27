package com.ptf.si.wp.zadaca1.models.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserUpdateIn {

  @Size(max = 255)
  @NotBlank(message = "Password ne smije biti prazan")
  private String password;

  @Size(max = 255)
  @NotBlank(message = "Password confirm ne smije biti prazan")
  private String passwordConfirm;

  @Size(max = 255)
  @NotBlank(message = "Trenutni password ne smije biti prazan")
  private String currentPassword;

  public UserUpdateIn() {
  }

  public UserUpdateIn(String password, String passwordConfirm, String currentPassword) {
    this.password = password;
    this.passwordConfirm = passwordConfirm;
    this.currentPassword = currentPassword;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public boolean passwordEquals(String password, String passwordConfirm) {
    return password.equals(passwordConfirm);
  }
}
