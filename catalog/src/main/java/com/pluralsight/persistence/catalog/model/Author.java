package com.pluralsight.persistence.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_authors")
public class Author extends Person {

  @Enumerated(EnumType.STRING)
  @Column(name = "preferred_language")
  public Language preferredLanguage;

  @URL
  @Size(max = 255)
  public String website;

  @JsonIgnore
  @ManyToMany(mappedBy = "authors")
  public List<Book> books = new ArrayList<>();

  // Custom query methods

  public static List<Author> findByPreferredLanguage(Language language) {
    return list("preferredLanguage", language);
  }

  public static List<Author> findBornBefore(LocalDate date) {
    return list("dateOfBirth < ?1", date);
  }

  public static long countByPreferredLanguage(Language language) {
    return count("preferredLanguage", language);
  }

  @Override
  public String toString() {
    return "Author{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", preferredLanguage=" + preferredLanguage +
      '}';
  }
}
