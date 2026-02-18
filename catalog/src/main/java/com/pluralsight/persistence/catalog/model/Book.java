package com.pluralsight.persistence.catalog.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.quarkus.panache.common.Parameters;

@Entity
@DiscriminatorValue("BOOK")
@NamedQuery(name = "Book.findByIdWithRelations",
  query = "SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.publisher LEFT JOIN FETCH b.authors WHERE b.id = :id")
public class Book extends Item {

  public static Optional<Book> findByIdWithRelations(Long id) {
    return find("#Book.findByIdWithRelations", Map.of("id", id)).singleResultOptional();
  }

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

  @ManyToMany
  @JoinTable(
    name = "t_book_authors",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  public List<Author> authors = new ArrayList<>();

  // Custom query methods

  public static Book findByIsbn(String isbn) {
    return find("isbn", isbn).firstResult();
  }

  public static List<Book> findByLanguage(Language language) {
    return list("language", language);
  }

  public static List<Book> findByPublisher(Publisher publisher) {
    return list("publisher", publisher);
  }

  public static List<Book> findByAuthor(Author author) {
    return list("SELECT b FROM Book b JOIN b.authors a WHERE a = ?1", author);
  }

  public static List<Book> findPublishedBefore(LocalDate date) {
    return list("publicationDate < ?1", date);
  }

  public static List<Book> findPublishedAfter(LocalDate date) {
    return list("publicationDate > ?1", date);
  }

  public static List<Book> findPublishedBetween(LocalDate start, LocalDate end) {
    return list("publicationDate >= ?1 and publicationDate <= ?2", start, end);
  }

  @Override
  public String toString() {
    return "Book{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", price=" + price +
      ", stock=" + stock +
      ", isbn='" + isbn + '\'' +
      ", language=" + language +
      '}';
  }
}
