package com.pluralsight.persistence.catalog.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "t_persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends PanacheEntity {

  @NotNull
  @Size(max = 50)
  @Column(name = "first_name", length = 50, nullable = false)
  public String firstName;

  @NotNull
  @Size(max = 50)
  @Column(name = "last_name", length = 50, nullable = false)
  public String lastName;

  @Size(max = 3000)
  @Column(length = 3000)
  public String bio;

  @Column(name = "date_of_birth")
  public LocalDate dateOfBirth;

  @Column(name = "created_date")
  public Instant createdDate;

  @PrePersist
  void prePersist() {
    createdDate = Instant.now();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      '}';
  }
}
