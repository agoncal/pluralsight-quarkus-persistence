package com.pluralsight.persistence.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_musicians")
public class Musician extends Person {

  @Size(max = 100)
  @Column(name = "stage_name", length = 100)
  public String stageName;

  @Size(max = 50)
  @Column(length = 50)
  public String instrument;

  @JsonIgnore
  @ManyToMany(mappedBy = "musicians")
  public List<CD> cds = new ArrayList<>();

  // Custom query methods

  public static List<Musician> findByInstrument(String instrument) {
    return list("instrument", instrument);
  }

  public static List<Musician> findByStageNameContaining(String keyword) {
    return list("lower(stageName) like lower(?1)", "%" + keyword + "%");
  }

  public static List<Musician> findBornBefore(LocalDate date) {
    return list("dateOfBirth < ?1", date);
  }

  public static List<Musician> findBornAfter(LocalDate date) {
    return list("dateOfBirth > ?1", date);
  }

  @Override
  public String toString() {
    return "Musician{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", stageName='" + stageName + '\'' +
      ", instrument='" + instrument + '\'' +
      '}';
  }
}
