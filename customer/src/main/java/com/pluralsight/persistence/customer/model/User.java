package com.pluralsight.persistence.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "t_users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(max = 50)
  @Column(length = 50, nullable = false, unique = true)
  private String username;

  @NotNull
  @Column(nullable = false)
  private String password;

  @NotNull
  @Email
  @Column(nullable = false, unique = true)
  private String email;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Column(nullable = false)
  private Boolean enabled = true;

  @Column(name = "last_login_date")
  private Instant lastLoginDate;

  @Column(name = "created_date")
  private Instant createdDate;

  @PrePersist
  void prePersist() {
    createdDate = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Instant getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(Instant lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }
}
