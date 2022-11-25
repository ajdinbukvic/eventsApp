package com.ptf.si.wp.zadaca1.models.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserIn {

  @Size(max = 255)
  @NotBlank(message = "Ime ne smije biti prazno")
  private String firstName;

  @Size(max = 255)
  @NotBlank(message = "Prezime ne smije biti prazno")
  private String lastName;

  @Size(max = 255)
  @NotBlank(message = "Email ne smije biti prazan")
  @Email
  private String email;

  @Size(max = 255)
  @NotBlank(message = "Password ne smije biti prazan")
  private String password;

  @Size(max = 255)
  @NotBlank(message = "Password confirm ne smije biti prazan")
  private String passwordConfirm;

  public UserIn() {
  }

  public UserIn(String firstName, String lastName, String email, String password, String passwordConfirm) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.passwordConfirm = passwordConfirm;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public boolean passwordEquals(String password, String passwordConfirm) {
    return password.equals(passwordConfirm);
  }
}
