package com.pluralsight.persistence.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Item {

  @Size(min = 13, max = 13)
  @Column(length = 13, unique = true)
  public String isbn;

  @Min(1)
  @Column(name = "nb_of_pages")
  public Integer nbOfPages;

  @PastOrPresent
  @Column(name = "publication_date")
  public LocalDate publicationDate;

  @Enumerated(EnumType.STRING)
  public Language language;

  @ManyToOne
  @JoinColumn(name = "publisher_fk")
  public Publisher publisher;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "t_book_authors",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  public List<Author> authors = new ArrayList<>();
}
