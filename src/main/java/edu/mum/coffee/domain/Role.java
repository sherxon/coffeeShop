package edu.mum.coffee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sherxon on 5/24/17.
 */
@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="role_id")
  private int id;

  @Column(name="role")
  private String role;
}
