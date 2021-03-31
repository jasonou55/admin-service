package com.jarvis.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
public class User extends GenericEntityImpl implements UserDetails {

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  private String phone;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") },
          foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private List<Role> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    if (getRoles() != null) {
      for (Role role : getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role.getName()));
      }
    }
    return authorities;
  }

  @Override public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  @Override public String getPassword() {
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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(final List<Role> roles) {
    this.roles = roles;
  }

  @Override public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(final boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  @Override public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(final boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  @Override public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(final boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  @Override public boolean isEnabled() {
    return enabled;
  }
}
