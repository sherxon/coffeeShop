package edu.mum.coffee.domain;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

/**
 * Created by sherxon on 5/24/17.
 */
public class FullUser {
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String password;
  private String type;

  public void setType(String type) {
    this.type = type;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getType() {
    return type;
  }
}
