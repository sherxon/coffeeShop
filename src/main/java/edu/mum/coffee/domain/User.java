package edu.mum.coffee.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

/**
 * Created by sherxon on 5/24/17.
 */
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private int id;


  @Column(name = "email")
  @Email(message = "*Please provide a valid Email")
  @NotEmpty(message = "*Please provide an email")
  private String email;

  @Column(name = "password")
  @Length(min = 5, message = "*Your password must have at least 5 characters")
  @NotEmpty(message = "*Please provide your password")
  @Transient
  private String password;


  @Column(name = "active")
  private int active;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Person person;

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
