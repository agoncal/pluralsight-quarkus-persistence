package com.pluralsight.persistence.catalog.model;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

@QuarkusTest
class BookTest {

  @Test
  @TestTransaction
  void shouldCreateBook() {
    Book book = new Book();
    book.title = "Test Book";
    book.price = new BigDecimal("29.99");
    book.stock = 100;
    book.isbn = "1234567890123";
    book.language = Language.ENGLISH;

    book.persist();

    assertNotNull(book.id);
  }

  @Test
  @TestTransaction
  void shouldFindBookById() {
    Book book = new Book();
    book.title = "Findable Book";
    book.price = new BigDecimal("19.99");
    book.stock = 50;
    book.isbn = "9876543210123";
    book.language = Language.FRENCH;
    book.persist();

    Book found = Book.findById(book.id);

    assertNotNull(found);
    assertEquals("Findable Book", found.title);
    assertEquals(Language.FRENCH, found.language);
  }

  @Test
  @TestTransaction
  void shouldNotFindBookById() {
    Book found = Book.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeleteBook() {
    Book book = new Book();
    book.title = "To Be Deleted";
    book.price = new BigDecimal("5.00");
    book.stock = 5;
    book.isbn = "4444444444444";
    book.language = Language.ENGLISH;
    book.persist();

    Long bookId = book.id;
    assertNotNull(Book.findById(bookId));

    boolean deleted = Book.deleteById(bookId);

    assertTrue(deleted);
    assertNull(Book.findById(bookId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingBook() {
    boolean deleted = Book.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountBooks() {
    long initialCount = Book.count();

    Book book = new Book();
    book.title = "Count Test Book";
    book.price = new BigDecimal("10.00");
    book.stock = 10;
    book.isbn = "8888888888888";
    book.language = Language.ENGLISH;
    book.persist();

    assertEquals(initialCount + 1, Book.count());
  }

  @Test
  @TestTransaction
  void shouldListAllBooks() {
    long initialCount = Book.count();

    Book book1 = new Book();
    book1.title = "Book One";
    book1.price = new BigDecimal("10.00");
    book1.stock = 10;
    book1.isbn = "1111111111111";
    book1.language = Language.ENGLISH;
    book1.persist();

    Book book2 = new Book();
    book2.title = "Book Two";
    book2.price = new BigDecimal("20.00");
    book2.stock = 20;
    book2.isbn = "2222222222222";
    book2.language = Language.SPANISH;
    book2.persist();

    List<Book> books = Book.listAll();

    assertEquals(initialCount + 2, books.size());
  }

  @Test
  @TestTransaction
  void shouldFindBookByLanguage() {
    Book germanBook = new Book();
    germanBook.title = "German Novel";
    germanBook.price = new BigDecimal("15.00");
    germanBook.stock = 30;
    germanBook.isbn = "3333333333333";
    germanBook.language = Language.GERMAN;
    germanBook.persist();

    List<Book> germanBooks = Book.list("language", Language.GERMAN);

    assertTrue(germanBooks.stream().anyMatch(b -> b.title.equals("German Novel")));
    assertTrue(germanBooks.stream().allMatch(b -> b.language == Language.GERMAN));
  }

  @Test
  @TestTransaction
  void shouldNotFindBookByLanguage() {
    Book.deleteAll();

    List<Book> books = Book.list("language", Language.PORTUGUESE);

    assertTrue(books.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnBook() {
    // Clear all books
    Book.deleteAll();

    // Find all and check there is zero
    assertEquals(0, Book.count());
    assertTrue(Book.listAll().isEmpty());

    // Create two items
    Book book1 = new Book();
    book1.title = "First Book";
    book1.price = new BigDecimal("19.99");
    book1.stock = 10;
    book1.isbn = "1111111111111";
    book1.language = Language.ENGLISH;
    book1.persist();

    Book book2 = new Book();
    book2.title = "Second Book";
    book2.price = new BigDecimal("29.99");
    book2.stock = 20;
    book2.isbn = "2222222222222";
    book2.language = Language.FRENCH;
    book2.persist();

    // Find all and check there is two
    assertEquals(2, Book.count());
    assertEquals(2, Book.listAll().size());

    // Find by id and check it's the right one
    Book foundBook1 = Book.findById(book1.id);
    assertNotNull(foundBook1);
    assertEquals("First Book", foundBook1.title);
    assertEquals(new BigDecimal("19.99"), foundBook1.price);
    assertEquals(Language.ENGLISH, foundBook1.language);

    // Update one item, find by id and check it's ok
    foundBook1.title = "Updated First Book";
    foundBook1.price = new BigDecimal("24.99");
    Book updatedBook = Book.findById(book1.id);
    assertEquals("Updated First Book", updatedBook.title);
    assertEquals(new BigDecimal("24.99"), updatedBook.price);

    // Delete one item and check there is only one now
    Book.deleteById(book1.id);
    assertEquals(1, Book.count());
    assertNull(Book.findById(book1.id));
    assertNotNull(Book.findById(book2.id));

    // Delete the second item and check findall returns zero
    Book.deleteById(book2.id);
    assertEquals(0, Book.count());
    assertTrue(Book.listAll().isEmpty());
  }
}
