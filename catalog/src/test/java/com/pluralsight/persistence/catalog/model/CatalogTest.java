package com.pluralsight.persistence.catalog.model;

import com.pluralsight.persistence.catalog.repository.CustomerRepository;
import com.pluralsight.persistence.catalog.repository.PurchaseOrderRepository;
import com.pluralsight.persistence.catalog.repository.UserRepository;
import com.pluralsight.persistence.customer.model.Address;
import com.pluralsight.persistence.customer.model.Customer;
import com.pluralsight.persistence.customer.model.OrderStatus;
import com.pluralsight.persistence.customer.model.PurchaseOrder;
import com.pluralsight.persistence.customer.model.User;
import com.pluralsight.persistence.customer.model.UserRole;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class CatalogTest {

  @Inject
  UserRepository userRepository;

  @Inject
  CustomerRepository customerRepository;

  @Inject
  PurchaseOrderRepository purchaseOrderRepository;

  // ======================================
  // Book Tests (Active Record Pattern)
  // ======================================

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
  void shouldFindBooksByLanguage() {
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
  void shouldUpdateBook() {
    Book book = new Book();
    book.title = "Original Title";
    book.price = new BigDecimal("25.00");
    book.stock = 100;
    book.isbn = "5555555555555";
    book.language = Language.ENGLISH;
    book.persist();

    book.title = "Updated Title";
    book.price = new BigDecimal("30.00");

    Book updated = Book.findById(book.id);

    assertEquals("Updated Title", updated.title);
    assertEquals(new BigDecimal("30.00"), updated.price);
  }

  // ======================================
  // CD Tests (Active Record Pattern)
  // ======================================

  @Test
  @TestTransaction
  void shouldCreateCD() {
    CD cd = new CD();
    cd.title = "Test Album";
    cd.price = new BigDecimal("14.99");
    cd.stock = 50;
    cd.ean = "1234567890123";
    cd.musicCompany = "Test Records";
    cd.genre = MusicGenre.ROCK;

    cd.persist();

    assertNotNull(cd.id);
  }

  @Test
  @TestTransaction
  void shouldFindCDById() {
    CD cd = new CD();
    cd.title = "Findable Album";
    cd.price = new BigDecimal("12.99");
    cd.stock = 25;
    cd.ean = "9876543210123";
    cd.musicCompany = "Indie Label";
    cd.genre = MusicGenre.JAZZ;
    cd.persist();

    CD found = CD.findById(cd.id);

    assertNotNull(found);
    assertEquals("Findable Album", found.title);
    assertEquals("Indie Label", found.musicCompany);
    assertEquals(MusicGenre.JAZZ, found.genre);
  }

  @Test
  @TestTransaction
  void shouldFindCDsByGenre() {
    CD classicalCD = new CD();
    classicalCD.title = "Symphony No. 5";
    classicalCD.price = new BigDecimal("18.00");
    classicalCD.stock = 15;
    classicalCD.ean = "6666666666666";
    classicalCD.musicCompany = "Classical Records";
    classicalCD.genre = MusicGenre.CLASSICAL;
    classicalCD.persist();

    List<CD> classicalCDs = CD.list("genre", MusicGenre.CLASSICAL);

    assertTrue(classicalCDs.stream().anyMatch(c -> c.title.equals("Symphony No. 5")));
    assertTrue(classicalCDs.stream().allMatch(c -> c.genre == MusicGenre.CLASSICAL));
  }

  @Test
  @TestTransaction
  void shouldDeleteCD() {
    CD cd = new CD();
    cd.title = "To Be Deleted Album";
    cd.price = new BigDecimal("9.99");
    cd.stock = 10;
    cd.ean = "7777777777777";
    cd.musicCompany = "Temp Records";
    cd.genre = MusicGenre.POP;
    cd.persist();

    Long cdId = cd.id;
    assertNotNull(CD.findById(cdId));

    boolean deleted = CD.deleteById(cdId);

    assertTrue(deleted);
    assertNull(CD.findById(cdId));
  }

  // ======================================
  // Author Tests (Active Record Pattern)
  // ======================================

  @Test
  @TestTransaction
  void shouldCreateAuthor() {
    Author author = new Author();
    author.firstName = "Jane";
    author.lastName = "Doe";
    author.bio = "Famous author";
    author.preferredLanguage = Language.ENGLISH;

    author.persist();

    assertNotNull(author.id);
  }

  @Test
  @TestTransaction
  void shouldFindAuthorById() {
    Author author = new Author();
    author.firstName = "John";
    author.lastName = "Smith";
    author.bio = "Prolific writer";
    author.preferredLanguage = Language.FRENCH;
    author.persist();

    Author found = Author.findById(author.id);

    assertNotNull(found);
    assertEquals("John", found.firstName);
    assertEquals("Smith", found.lastName);
    assertEquals(Language.FRENCH, found.preferredLanguage);
  }

  @Test
  @TestTransaction
  void shouldDeleteAuthor() {
    Author author = new Author();
    author.firstName = "To Be";
    author.lastName = "Deleted";
    author.bio = "Temporary author";
    author.preferredLanguage = Language.SPANISH;
    author.persist();

    Long authorId = author.id;
    assertNotNull(Author.findById(authorId));

    boolean deleted = Author.deleteById(authorId);

    assertTrue(deleted);
    assertNull(Author.findById(authorId));
  }

  // ======================================
  // Musician Tests (Active Record Pattern)
  // ======================================

  @Test
  @TestTransaction
  void shouldCreateMusician() {
    Musician musician = new Musician();
    musician.firstName = "John";
    musician.lastName = "Lennon";
    musician.stageName = "Johnny";
    musician.instrument = "Guitar";

    musician.persist();

    assertNotNull(musician.id);
  }

  @Test
  @TestTransaction
  void shouldFindMusicianById() {
    Musician musician = new Musician();
    musician.firstName = "Paul";
    musician.lastName = "McCartney";
    musician.stageName = "Macca";
    musician.instrument = "Bass";
    musician.persist();

    Musician found = Musician.findById(musician.id);

    assertNotNull(found);
    assertEquals("Paul", found.firstName);
    assertEquals("Macca", found.stageName);
    assertEquals("Bass", found.instrument);
  }

  @Test
  @TestTransaction
  void shouldDeleteMusician() {
    Musician musician = new Musician();
    musician.firstName = "Temp";
    musician.lastName = "Musician";
    musician.stageName = "Temp";
    musician.instrument = "Drums";
    musician.persist();

    Long musicianId = musician.id;
    assertNotNull(Musician.findById(musicianId));

    boolean deleted = Musician.deleteById(musicianId);

    assertTrue(deleted);
    assertNull(Musician.findById(musicianId));
  }

  // ======================================
  // Full CRUD Tests
  // ======================================

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

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnCD() {
    // Clear all tracks first (due to FK constraint), then CDs
    Track.deleteAll();
    CD.deleteAll();

    // Find all and check there is zero
    assertEquals(0, CD.count());
    assertTrue(CD.listAll().isEmpty());

    // Create two items
    CD cd1 = new CD();
    cd1.title = "First Album";
    cd1.price = new BigDecimal("14.99");
    cd1.stock = 50;
    cd1.ean = "1111111111111";
    cd1.musicCompany = "Rock Records";
    cd1.genre = MusicGenre.ROCK;
    cd1.persist();

    CD cd2 = new CD();
    cd2.title = "Second Album";
    cd2.price = new BigDecimal("17.99");
    cd2.stock = 30;
    cd2.ean = "2222222222222";
    cd2.musicCompany = "Jazz Label";
    cd2.genre = MusicGenre.JAZZ;
    cd2.persist();

    // Find all and check there is two
    assertEquals(2, CD.count());
    assertEquals(2, CD.listAll().size());

    // Find by id and check it's the right one
    CD foundCD1 = CD.findById(cd1.id);
    assertNotNull(foundCD1);
    assertEquals("First Album", foundCD1.title);
    assertEquals("Rock Records", foundCD1.musicCompany);
    assertEquals(MusicGenre.ROCK, foundCD1.genre);

    // Update one item, find by id and check it's ok
    foundCD1.title = "Updated First Album";
    foundCD1.price = new BigDecimal("19.99");
    CD updatedCD = CD.findById(cd1.id);
    assertEquals("Updated First Album", updatedCD.title);
    assertEquals(new BigDecimal("19.99"), updatedCD.price);

    // Delete one item and check there is only one now
    CD.deleteById(cd1.id);
    assertEquals(1, CD.count());
    assertNull(CD.findById(cd1.id));
    assertNotNull(CD.findById(cd2.id));

    // Delete the second item and check findall returns zero
    CD.deleteById(cd2.id);
    assertEquals(0, CD.count());
    assertTrue(CD.listAll().isEmpty());
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnAuthor() {
    // Clear all authors
    Author.deleteAll();

    // Find all and check there is zero
    assertEquals(0, Author.count());
    assertTrue(Author.listAll().isEmpty());

    // Create two items
    Author author1 = new Author();
    author1.firstName = "Jane";
    author1.lastName = "Austen";
    author1.bio = "English novelist";
    author1.preferredLanguage = Language.ENGLISH;
    author1.persist();

    Author author2 = new Author();
    author2.firstName = "Victor";
    author2.lastName = "Hugo";
    author2.bio = "French poet and novelist";
    author2.preferredLanguage = Language.FRENCH;
    author2.persist();

    // Find all and check there is two
    assertEquals(2, Author.count());
    assertEquals(2, Author.listAll().size());

    // Find by id and check it's the right one
    Author foundAuthor1 = Author.findById(author1.id);
    assertNotNull(foundAuthor1);
    assertEquals("Jane", foundAuthor1.firstName);
    assertEquals("Austen", foundAuthor1.lastName);
    assertEquals(Language.ENGLISH, foundAuthor1.preferredLanguage);

    // Update one item, find by id and check it's ok
    foundAuthor1.bio = "Famous English novelist";
    foundAuthor1.preferredLanguage = Language.SPANISH;
    Author updatedAuthor = Author.findById(author1.id);
    assertEquals("Famous English novelist", updatedAuthor.bio);
    assertEquals(Language.SPANISH, updatedAuthor.preferredLanguage);

    // Delete one item and check there is only one now
    Author.deleteById(author1.id);
    assertEquals(1, Author.count());
    assertNull(Author.findById(author1.id));
    assertNotNull(Author.findById(author2.id));

    // Delete the second item and check findall returns zero
    Author.deleteById(author2.id);
    assertEquals(0, Author.count());
    assertTrue(Author.listAll().isEmpty());
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnMusician() {
    // Clear all musicians
    Musician.deleteAll();

    // Find all and check there is zero
    assertEquals(0, Musician.count());
    assertTrue(Musician.listAll().isEmpty());

    // Create two items
    Musician musician1 = new Musician();
    musician1.firstName = "John";
    musician1.lastName = "Lennon";
    musician1.stageName = "Johnny";
    musician1.instrument = "Guitar";
    musician1.persist();

    Musician musician2 = new Musician();
    musician2.firstName = "Paul";
    musician2.lastName = "McCartney";
    musician2.stageName = "Macca";
    musician2.instrument = "Bass";
    musician2.delete();

    // Find all and check there is two
    assertEquals(2, Musician.count());
    assertEquals(2, Musician.listAll().size());

    // Find by id and check it's the right one
    Musician foundMusician1 = Musician.findById(musician1.id);
    assertNotNull(foundMusician1);
    assertEquals("John", foundMusician1.firstName);
    assertEquals("Johnny", foundMusician1.stageName);
    assertEquals("Guitar", foundMusician1.instrument);

    // Update one item, find by id and check it's ok
    foundMusician1.stageName = "John Lennon";
    foundMusician1.instrument = "Piano";
    Musician updatedMusician = Musician.findById(musician1.id);
    assertEquals("John Lennon", updatedMusician.stageName);
    assertEquals("Piano", updatedMusician.instrument);

    // Delete one item and check there is only one now
    Musician.deleteById(musician1.id);
    assertEquals(1, Musician.count());
    assertNull(Musician.findById(musician1.id));
    assertNotNull(Musician.findById(musician2.id));

    // Delete the second item and check findall returns zero
    Musician.deleteById(musician2.id);
    assertEquals(0, Musician.count());
    assertTrue(Musician.listAll().isEmpty());
  }

  // ======================================
  // Count Tests
  // ======================================

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
  void shouldCountCDs() {
    long initialCount = CD.count();

    CD cd = new CD();
    cd.title = "Count Test Album";
    cd.price = new BigDecimal("10.00");
    cd.stock = 10;
    cd.ean = "9999999999999";
    cd.musicCompany = "Count Records";
    cd.genre = MusicGenre.ROCK;
    cd.persist();

    assertEquals(initialCount + 1, CD.count());
  }

  @Test
  @TestTransaction
  void shouldCountAuthors() {
    long initialCount = Author.count();

    Author author = new Author();
    author.firstName = "Count";
    author.lastName = "Test";
    author.bio = "Author for counting";
    author.preferredLanguage = Language.ENGLISH;
    author.persist();

    assertEquals(initialCount + 1, Author.count());
  }

  @Test
  @TestTransaction
  void shouldCountMusicians() {
    long initialCount = Musician.count();

    Musician musician = new Musician();
    musician.firstName = "Count";
    musician.lastName = "Test";
    musician.stageName = "Counter";
    musician.instrument = "Piano";
    musician.persist();

    assertEquals(initialCount + 1, Musician.count());
  }

  // ======================================
  // PurchaseOrder Tests (Repository Pattern, no mocking)
  // ======================================

  @Test
  @TestTransaction
  void shouldCreateAndFindPurchaseOrder() {
    // Create User
    String uniqueUsername = "test" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    assertNotNull(user.getId());

    // Create Customer with billing address
    Address billingAddress = new Address();
    billingAddress.setStreet("123 Main St");
    billingAddress.setCity("New York");
    billingAddress.setState("NY");
    billingAddress.setZipCode("10001");
    billingAddress.setCountry("USA");

    Customer customer = new Customer();
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setPhone("555-1234");
    customer.setBillingAddress(billingAddress);
    customer.setUser(user);
    customerRepository.persist(customer);

    assertNotNull(customer.getId());

    // Create PurchaseOrder with shipping address
    Address shippingAddress = new Address();
    shippingAddress.setStreet("456 Oak Ave");
    shippingAddress.setCity("Los Angeles");
    shippingAddress.setState("CA");
    shippingAddress.setZipCode("90001");
    shippingAddress.setCountry("USA");

    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setOrderDate(LocalDateTime.of(2024, 1, 15, 10, 30));
    purchaseOrder.setStatus(OrderStatus.PENDING);
    purchaseOrder.setTotalAmount(new BigDecimal("99.99"));
    purchaseOrder.setCustomer(customer);
    purchaseOrder.setShippingAddress(shippingAddress);
    purchaseOrderRepository.persist(purchaseOrder);

    assertNotNull(purchaseOrder.getId());

    // Find the PurchaseOrder and verify all details
    PurchaseOrder found = purchaseOrderRepository.findById(purchaseOrder.getId());

    assertNotNull(found);
    assertEquals(OrderStatus.PENDING, found.getStatus());
    assertEquals(new BigDecimal("99.99"), found.getTotalAmount());
    assertNotNull(found.getCustomer());
    assertEquals("John", found.getCustomer().getFirstName());
    assertEquals("Doe", found.getCustomer().getLastName());
    assertNotNull(found.getShippingAddress());
    assertEquals("Los Angeles", found.getShippingAddress().getCity());
    assertEquals("CA", found.getShippingAddress().getState());
    assertEquals("90001", found.getShippingAddress().getZipCode());
  }
}
