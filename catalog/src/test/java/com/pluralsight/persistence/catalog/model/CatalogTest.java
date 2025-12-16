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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class CatalogTest {

  private static final String UNIQUE_SUFFIX = UUID.randomUUID().toString().substring(0, 8);

  @Inject
  UserRepository userRepository;

  @Inject
  CustomerRepository customerRepository;

  @Inject
  PurchaseOrderRepository purchaseOrderRepository;

  @Test
  @TestTransaction
  void shouldCreateAndFindUser() {
    User user = new User();
    user.setUsername("testuser_" + UNIQUE_SUFFIX);
    user.setPassword("password123");
    user.setEmail("testuser_" + UNIQUE_SUFFIX + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    userRepository.persist(user);

    assertNotNull(user.getId());
    User found = userRepository.findById(user.getId());
    assertNotNull(found);
    assertEquals(user.getUsername(), found.getUsername());
    assertEquals(user.getEmail(), found.getEmail());
    assertEquals(UserRole.CUSTOMER, found.getRole());
  }

  @Test
  @TestTransaction
  void shouldCreateAndFindCustomer() {
    // First create a user
    User user = new User();
    user.setUsername("customeruser_" + UNIQUE_SUFFIX);
    user.setPassword("password123");
    user.setEmail("customeruser_" + UNIQUE_SUFFIX + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    // Create addresses
    Address billingAddress = new Address();
    billingAddress.setStreet("123 Main St");
    billingAddress.setCity("New York");
    billingAddress.setState("NY");
    billingAddress.setZipCode("10001");
    billingAddress.setCountry("USA");

    Address shippingAddress = new Address();
    shippingAddress.setStreet("456 Oak Ave");
    shippingAddress.setCity("Los Angeles");
    shippingAddress.setState("CA");
    shippingAddress.setZipCode("90001");
    shippingAddress.setCountry("USA");

    // Create customer
    Customer customer = new Customer();
    customer.setFirstName("John");
    customer.setLastName("Doe_" + UNIQUE_SUFFIX);
    customer.setPhone("555-1234");
    customer.setBillingAddress(billingAddress);
    customer.setShippingAddress(shippingAddress);
    customer.setUser(user);

    customerRepository.persist(customer);

    assertNotNull(customer.getId());
    Customer found = customerRepository.findById(customer.getId());
    assertNotNull(found);
    assertEquals("John", found.getFirstName());
    assertEquals("Doe_" + UNIQUE_SUFFIX, found.getLastName());
    assertNotNull(found.getBillingAddress());
    assertEquals("New York", found.getBillingAddress().getCity());
  }

  @Test
  @TestTransaction
  void shouldCreateAndFindAuthor() {
    Author author = new Author();
    author.firstName = "Jane";
    author.lastName = "Author_" + UNIQUE_SUFFIX;
    author.bio = "A famous writer of classic literature";
    author.dateOfBirth = LocalDate.of(1975, 3, 15);
    author.preferredLanguage = Language.ENGLISH;
    author.website = "https://janeauthor.example.com";

    author.persist();

    assertNotNull(author.id);
    Author found = Author.findById(author.id);
    assertNotNull(found);
    assertEquals("Jane", found.firstName);
    assertEquals("Author_" + UNIQUE_SUFFIX, found.lastName);
    assertEquals(Language.ENGLISH, found.preferredLanguage);
  }

  @Test
  @TestTransaction
  void shouldCreateAndFindBook() {
    String uniqueIsbn = String.format("%013d", System.nanoTime() % 10000000000000L);

    Book book = new Book();
    book.title = "The Great Novel " + UNIQUE_SUFFIX;
    book.description = "An epic tale of adventure and discovery";
    book.price = new BigDecimal("24.99");
    book.stock = 100;
    book.isbn = uniqueIsbn;
    book.nbOfPages = 450;
    book.publicationDate = LocalDate.of(2024, 1, 15);
    book.language = Language.ENGLISH;

    book.persist();

    assertNotNull(book.id);
    Book found = Book.findById(book.id);
    assertNotNull(found);
    assertEquals("The Great Novel " + UNIQUE_SUFFIX, found.title);
    assertEquals(new BigDecimal("24.99"), found.price);
    assertEquals(100, found.stock);
    assertEquals(uniqueIsbn, found.isbn);
    assertEquals(450, found.nbOfPages);
    assertEquals(Language.ENGLISH, found.language);
  }

  @Test
  @TestTransaction
  void shouldCreateAndFindMusician() {
    Musician musician = new Musician();
    musician.firstName = "John";
    musician.lastName = "Musician_" + UNIQUE_SUFFIX;
    musician.bio = "Lead guitarist with 20 years of experience";
    musician.dateOfBirth = LocalDate.of(1980, 7, 22);
    musician.stageName = "Johnny Guitar";
    musician.instrument = "Guitar";

    musician.persist();

    assertNotNull(musician.id);
    Musician found = Musician.findById(musician.id);
    assertNotNull(found);
    assertEquals("John", found.firstName);
    assertEquals("Johnny Guitar", found.stageName);
    assertEquals("Guitar", found.instrument);
  }

  @Test
  @TestTransaction
  void shouldCreateAndFindCD() {
    String uniqueEan = String.format("%013d", System.nanoTime() % 10000000000000L);

    CD cd = new CD();
    cd.title = "Greatest Hits " + UNIQUE_SUFFIX;
    cd.description = "A collection of timeless classics";
    cd.price = new BigDecimal("19.99");
    cd.stock = 50;
    cd.ean = uniqueEan;
    cd.musicCompany = "Vintage Records";
    cd.genre = MusicGenre.ROCK;
    cd.releaseDate = LocalDate.of(2024, 6, 1);

    cd.persist();

    assertNotNull(cd.id);
    CD found = CD.findById(cd.id);
    assertNotNull(found);
    assertEquals("Greatest Hits " + UNIQUE_SUFFIX, found.title);
    assertEquals(new BigDecimal("19.99"), found.price);
    assertEquals(50, found.stock);
    assertEquals(uniqueEan, found.ean);
    assertEquals("Vintage Records", found.musicCompany);
    assertEquals(MusicGenre.ROCK, found.genre);
  }

  @Test
  @TestTransaction
  void shouldCreateAndFindPurchaseOrder() {
    // First create user and customer
    User user = new User();
    user.setUsername("orderuser_" + UNIQUE_SUFFIX);
    user.setPassword("password123");
    user.setEmail("orderuser_" + UNIQUE_SUFFIX + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    Address billingAddress = new Address();
    billingAddress.setStreet("123 Main St");
    billingAddress.setCity("New York");
    billingAddress.setState("NY");
    billingAddress.setZipCode("10001");
    billingAddress.setCountry("USA");

    Customer customer = new Customer();
    customer.setFirstName("Order");
    customer.setLastName("Customer_" + UNIQUE_SUFFIX);
    customer.setPhone("555-9999");
    customer.setBillingAddress(billingAddress);
    customer.setUser(user);
    customerRepository.persist(customer);

    // Create shipping address for order
    Address shippingAddress = new Address();
    shippingAddress.setStreet("789 Delivery Lane");
    shippingAddress.setCity("Chicago");
    shippingAddress.setState("IL");
    shippingAddress.setZipCode("60601");
    shippingAddress.setCountry("USA");

    // Create purchase order
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setOrderDate(LocalDateTime.now());
    purchaseOrder.setStatus(OrderStatus.PENDING);
    purchaseOrder.setTotalAmount(new BigDecimal("64.97"));
    purchaseOrder.setCustomer(customer);
    purchaseOrder.setShippingAddress(shippingAddress);

    purchaseOrderRepository.persist(purchaseOrder);

    assertNotNull(purchaseOrder.getId());
    PurchaseOrder found = purchaseOrderRepository.findById(purchaseOrder.getId());
    assertNotNull(found);
    assertEquals(OrderStatus.PENDING, found.getStatus());
    assertEquals(new BigDecimal("64.97"), found.getTotalAmount());
    assertNotNull(found.getCustomer());
    assertEquals(customer.getId(), found.getCustomer().getId());
  }

  @Test
  @TestTransaction
  void shouldCreateBookWithAuthor() {
    // Create author
    Author author = new Author();
    author.firstName = "Famous";
    author.lastName = "Writer_" + UNIQUE_SUFFIX;
    author.bio = "Award-winning author";
    author.dateOfBirth = LocalDate.of(1960, 5, 20);
    author.preferredLanguage = Language.ENGLISH;
    author.persist();

    // Create book with author
    String uniqueIsbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    Book book = new Book();
    book.title = "Book With Author " + UNIQUE_SUFFIX;
    book.description = "A book written by a famous author";
    book.price = new BigDecimal("29.99");
    book.stock = 75;
    book.isbn = uniqueIsbn;
    book.nbOfPages = 300;
    book.language = Language.ENGLISH;
    book.authors = List.of(author);

    book.persist();

    assertNotNull(book.id);
    Book found = Book.findById(book.id);
    assertNotNull(found);
    assertEquals("Book With Author " + UNIQUE_SUFFIX, found.title);
    assertNotNull(found.authors);
    assertEquals(1, found.authors.size());
    assertEquals(author.id, found.authors.get(0).id);
  }

  @Test
  @TestTransaction
  void shouldCreateCDWithMusicians() {
    // Create musicians
    Musician musician1 = new Musician();
    musician1.firstName = "Lead";
    musician1.lastName = "Singer_" + UNIQUE_SUFFIX;
    musician1.stageName = "The Voice";
    musician1.instrument = "Vocals";
    musician1.persist();

    Musician musician2 = new Musician();
    musician2.firstName = "Bass";
    musician2.lastName = "Player_" + UNIQUE_SUFFIX;
    musician2.stageName = "Groovy Bass";
    musician2.instrument = "Bass Guitar";
    musician2.persist();

    // Create CD with musicians
    String uniqueEan = String.format("%013d", System.nanoTime() % 10000000000000L);
    CD cd = new CD();
    cd.title = "CD With Musicians " + UNIQUE_SUFFIX;
    cd.description = "A collaborative album";
    cd.price = new BigDecimal("22.99");
    cd.stock = 30;
    cd.ean = uniqueEan;
    cd.musicCompany = "Indie Label";
    cd.genre = MusicGenre.JAZZ;
    cd.musicians = List.of(musician1, musician2);

    cd.persist();

    assertNotNull(cd.id);
    CD found = CD.findById(cd.id);
    assertNotNull(found);
    assertEquals("CD With Musicians " + UNIQUE_SUFFIX, found.title);
    assertNotNull(found.musicians);
    assertEquals(2, found.musicians.size());
  }

  @Test
  @TestTransaction
  void shouldCountEntities() {
    long initialBookCount = Book.count();
    long initialCDCount = CD.count();

    // Create a book
    Book book = new Book();
    book.title = "Count Test Book " + UNIQUE_SUFFIX;
    book.price = new BigDecimal("15.00");
    book.stock = 10;
    book.isbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    book.persist();

    // Create a CD
    CD cd = new CD();
    cd.title = "Count Test CD " + UNIQUE_SUFFIX;
    cd.price = new BigDecimal("12.00");
    cd.stock = 5;
    cd.ean = String.format("%013d", System.nanoTime() % 10000000000000L);
    cd.persist();

    assertEquals(initialBookCount + 1, Book.count());
    assertEquals(initialCDCount + 1, CD.count());
  }

  @Test
  @TestTransaction
  void shouldFindBooksByLanguage() {
    // Create books with different languages
    Book englishBook = new Book();
    englishBook.title = "English Book " + UNIQUE_SUFFIX;
    englishBook.price = new BigDecimal("20.00");
    englishBook.stock = 10;
    englishBook.isbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    englishBook.language = Language.ENGLISH;
    englishBook.persist();

    Book frenchBook = new Book();
    frenchBook.title = "French Book " + UNIQUE_SUFFIX;
    frenchBook.price = new BigDecimal("18.00");
    frenchBook.stock = 8;
    frenchBook.isbn = String.format("%013d", (System.nanoTime() + 1) % 10000000000000L);
    frenchBook.language = Language.FRENCH;
    frenchBook.persist();

    List<Book> englishBooks = Book.list("language", Language.ENGLISH);
    List<Book> frenchBooks = Book.list("language", Language.FRENCH);

    assertTrue(englishBooks.stream().anyMatch(b -> b.title.equals("English Book " + UNIQUE_SUFFIX)));
    assertTrue(frenchBooks.stream().anyMatch(b -> b.title.equals("French Book " + UNIQUE_SUFFIX)));
  }

  @Test
  @TestTransaction
  void shouldFindCDsByGenre() {
    // Create CDs with different genres
    CD rockCD = new CD();
    rockCD.title = "Rock CD " + UNIQUE_SUFFIX;
    rockCD.price = new BigDecimal("16.00");
    rockCD.stock = 20;
    rockCD.ean = String.format("%013d", System.nanoTime() % 10000000000000L);
    rockCD.genre = MusicGenre.ROCK;
    rockCD.persist();

    CD jazzCD = new CD();
    jazzCD.title = "Jazz CD " + UNIQUE_SUFFIX;
    jazzCD.price = new BigDecimal("14.00");
    jazzCD.stock = 15;
    jazzCD.ean = String.format("%013d", (System.nanoTime() + 1) % 10000000000000L);
    jazzCD.genre = MusicGenre.JAZZ;
    jazzCD.persist();

    List<CD> rockCDs = CD.list("genre", MusicGenre.ROCK);
    List<CD> jazzCDs = CD.list("genre", MusicGenre.JAZZ);

    assertTrue(rockCDs.stream().anyMatch(c -> c.title.equals("Rock CD " + UNIQUE_SUFFIX)));
    assertTrue(jazzCDs.stream().anyMatch(c -> c.title.equals("Jazz CD " + UNIQUE_SUFFIX)));
  }

  @Test
  @TestTransaction
  void shouldUpdateBook() {
    Book book = new Book();
    book.title = "Original Title " + UNIQUE_SUFFIX;
    book.price = new BigDecimal("20.00");
    book.stock = 50;
    book.isbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    book.persist();

    // Update the book
    book.title = "Updated Title " + UNIQUE_SUFFIX;
    book.price = new BigDecimal("25.00");
    book.stock = 75;

    Book found = Book.findById(book.id);
    assertEquals("Updated Title " + UNIQUE_SUFFIX, found.title);
    assertEquals(new BigDecimal("25.00"), found.price);
    assertEquals(75, found.stock);
  }

  @Test
  @TestTransaction
  void shouldDeleteBook() {
    Book book = new Book();
    book.title = "To Be Deleted " + UNIQUE_SUFFIX;
    book.price = new BigDecimal("10.00");
    book.stock = 5;
    book.isbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    book.persist();

    Long bookId = book.id;
    assertNotNull(Book.findById(bookId));

    book.delete();

    assertNull(Book.findById(bookId));
  }
}
