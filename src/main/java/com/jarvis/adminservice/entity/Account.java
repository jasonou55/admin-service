package com.jarvis.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Repository
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column
  private String phone;

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(final String phone) {
    this.phone = phone;
  }
}
