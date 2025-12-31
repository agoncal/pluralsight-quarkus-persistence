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
import java.time.LocalDate;
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
  void shouldDeleteAllBooks() {
    Book.deleteAll();

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

    assertEquals(2, Book.count());

    Book.deleteAll();

    assertEquals(0, Book.count());
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

    List<Book> germanBooks = Book.findByLanguage(Language.GERMAN);

    assertTrue(germanBooks.stream().anyMatch(b -> b.title.equals("German Novel")));
    assertTrue(germanBooks.stream().allMatch(b -> b.language == Language.GERMAN));
  }

  @Test
  @TestTransaction
  void shouldNotFindBookByLanguage() {
    Book.deleteAll();

    List<Book> books = Book.findByLanguage(Language.PORTUGUESE);

    assertTrue(books.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindBookByIsbn() {
    Book book = new Book();
    book.title = "ISBN Test Book";
    book.price = new BigDecimal("25.00");
    book.stock = 15;
    book.isbn = "5555555555555";
    book.language = Language.ENGLISH;
    book.persist();

    Book found = Book.findByIsbn("5555555555555");

    assertNotNull(found);
    assertEquals("ISBN Test Book", found.title);
  }

  @Test
  @TestTransaction
  void shouldNotFindBookByIsbn() {
    Book found = Book.findByIsbn("0000000000000");

    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldFindBookByPublisher() {
    Publisher publisher = new Publisher();
    publisher.name = "Test Publisher";
    publisher.persist();

    Book book = new Book();
    book.title = "Published Book";
    book.price = new BigDecimal("30.00");
    book.stock = 20;
    book.isbn = "6666666666666";
    book.language = Language.ENGLISH;
    book.publisher = publisher;
    book.persist();

    List<Book> books = Book.findByPublisher(publisher);

    assertEquals(1, books.size());
    assertEquals("Published Book", books.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindBookByPublisher() {
    Publisher publisher = new Publisher();
    publisher.name = "Empty Publisher";
    publisher.persist();

    List<Book> books = Book.findByPublisher(publisher);

    assertTrue(books.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindBookByAuthor() {
    Author author = new Author();
    author.firstName = "John";
    author.lastName = "Writer";
    author.persist();

    Book book = new Book();
    book.title = "Authored Book";
    book.price = new BigDecimal("35.00");
    book.stock = 25;
    book.isbn = "7777777777777";
    book.language = Language.ENGLISH;
    book.authors.add(author);
    book.persist();

    List<Book> books = Book.findByAuthor(author);

    assertEquals(1, books.size());
    assertEquals("Authored Book", books.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindBookByAuthor() {
    Author author = new Author();
    author.firstName = "Unknown";
    author.lastName = "Author";
    author.persist();

    List<Book> books = Book.findByAuthor(author);

    assertTrue(books.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindBookPublishedBefore() {
    Book.deleteAll();

    Book oldBook = new Book();
    oldBook.title = "Old Book";
    oldBook.price = new BigDecimal("15.00");
    oldBook.stock = 10;
    oldBook.isbn = "8888888888881";
    oldBook.language = Language.ENGLISH;
    oldBook.publicationDate = LocalDate.of(2000, 1, 1);
    oldBook.persist();

    Book newBook = new Book();
    newBook.title = "New Book";
    newBook.price = new BigDecimal("25.00");
    newBook.stock = 20;
    newBook.isbn = "8888888888882";
    newBook.language = Language.ENGLISH;
    newBook.publicationDate = LocalDate.of(2023, 1, 1);
    newBook.persist();

    List<Book> books = Book.findPublishedBefore(LocalDate.of(2010, 1, 1));

    assertEquals(1, books.size());
    assertEquals("Old Book", books.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindBookPublishedBefore() {
    Book.deleteAll();

    Book book = new Book();
    book.title = "Recent Book";
    book.price = new BigDecimal("20.00");
    book.stock = 15;
    book.isbn = "9999999999991";
    book.language = Language.ENGLISH;
    book.publicationDate = LocalDate.of(2023, 6, 1);
    book.persist();

    List<Book> books = Book.findPublishedBefore(LocalDate.of(2020, 1, 1));

    assertTrue(books.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindBookPublishedAfter() {
    Book.deleteAll();

    Book oldBook = new Book();
    oldBook.title = "Old Book";
    oldBook.price = new BigDecimal("15.00");
    oldBook.stock = 10;
    oldBook.isbn = "1111111111112";
    oldBook.language = Language.ENGLISH;
    oldBook.publicationDate = LocalDate.of(2000, 1, 1);
    oldBook.persist();

    Book newBook = new Book();
    newBook.title = "New Book";
    newBook.price = new BigDecimal("25.00");
    newBook.stock = 20;
    newBook.isbn = "1111111111113";
    newBook.language = Language.ENGLISH;
    newBook.publicationDate = LocalDate.of(2023, 1, 1);
    newBook.persist();

    List<Book> books = Book.findPublishedAfter(LocalDate.of(2010, 1, 1));

    assertEquals(1, books.size());
    assertEquals("New Book", books.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldFindBookPublishedBetween() {
    Book.deleteAll();

    Book book2000 = new Book();
    book2000.title = "Book 2000";
    book2000.price = new BigDecimal("10.00");
    book2000.stock = 5;
    book2000.isbn = "2222222222221";
    book2000.language = Language.ENGLISH;
    book2000.publicationDate = LocalDate.of(2000, 6, 1);
    book2000.persist();

    Book book2015 = new Book();
    book2015.title = "Book 2015";
    book2015.price = new BigDecimal("20.00");
    book2015.stock = 10;
    book2015.isbn = "2222222222222";
    book2015.language = Language.ENGLISH;
    book2015.publicationDate = LocalDate.of(2015, 6, 1);
    book2015.persist();

    Book book2023 = new Book();
    book2023.title = "Book 2023";
    book2023.price = new BigDecimal("30.00");
    book2023.stock = 15;
    book2023.isbn = "2222222222223";
    book2023.language = Language.ENGLISH;
    book2023.publicationDate = LocalDate.of(2023, 6, 1);
    book2023.persist();

    List<Book> books = Book.findPublishedBetween(LocalDate.of(2010, 1, 1), LocalDate.of(2020, 12, 31));

    assertEquals(1, books.size());
    assertEquals("Book 2015", books.get(0).title);
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
