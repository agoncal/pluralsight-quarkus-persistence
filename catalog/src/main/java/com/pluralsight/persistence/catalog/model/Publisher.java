package com.pluralsight.persistence.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_publishers")
@Cacheable
public class Publisher extends PanacheEntity {

  @NotNull
  @Size(max = 100)
  @Column(length = 100, nullable = false, unique = true)
  public String name;

  @Size(max = 50)
  @Column(length = 50)
  public String country;

  @URL
  @Size(max = 255)
  public String website;

  @Column(name = "founded_year")
  public Integer foundedYear;

  @JsonIgnore
  @OneToMany(mappedBy = "publisher")
  public List<Book> books = new ArrayList<>();

  @Column(name = "created_date")
  public Instant createdDate;

  @PrePersist
  void prePersist() {
    createdDate = Instant.now();
  }

  // Custom query methods

  public static List<Publisher> findByNameContaining(String keyword) {
    return list("lower(name) like lower(?1)", "%" + keyword + "%");
  }

  public static List<Publisher> findByCountry(String country) {
    return list("country", country);
  }

  @Override
  public String toString() {
    return "Publisher{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", country='" + country + '\'' +
      '}';
  }
}
