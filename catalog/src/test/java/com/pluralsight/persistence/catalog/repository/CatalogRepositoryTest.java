package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.catalog.model.Author;
import com.pluralsight.persistence.catalog.model.Book;
import com.pluralsight.persistence.catalog.model.CD;
import com.pluralsight.persistence.catalog.model.Language;
import com.pluralsight.persistence.catalog.model.MusicGenre;
import com.pluralsight.persistence.catalog.model.Musician;
import com.pluralsight.persistence.customer.model.Address;
import com.pluralsight.persistence.customer.model.Customer;
import com.pluralsight.persistence.customer.model.OrderStatus;
import com.pluralsight.persistence.customer.model.PurchaseOrder;
import com.pluralsight.persistence.customer.model.User;
import com.pluralsight.persistence.customer.model.UserRole;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@QuarkusTest
class CatalogRepositoryTest {

  // Repository mocks using @InjectMock
  @InjectMock
  UserRepository userRepository;

  @InjectMock
  CustomerRepository customerRepository;

  @InjectMock
  PurchaseOrderRepository purchaseOrderRepository;

  // ======================================
  // Active Record Pattern Mocking (PanacheMock)
  // ======================================

  @Test
  void shouldMockBookCount() {
    PanacheMock.mock(Book.class);

    Mockito.when(Book.count()).thenReturn(42L);

    assertEquals(42L, Book.count());

    PanacheMock.verify(Book.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockBookFindById() {
    PanacheMock.mock(Book.class);

    Book mockBook = new Book();
    mockBook.id = 1L;
    mockBook.title = "Mocked Book Title";
    mockBook.price = new BigDecimal("29.99");
    mockBook.stock = 100;
    mockBook.isbn = "1234567890123";
    mockBook.language = Language.ENGLISH;

    Mockito.when(Book.findById(1L)).thenReturn(mockBook);

    Book found = Book.findById(1L);

    assertNotNull(found);
    assertEquals("Mocked Book Title", found.title);
    assertEquals(new BigDecimal("29.99"), found.price);
    assertEquals(Language.ENGLISH, found.language);

    PanacheMock.verify(Book.class, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockBookListAll() {
    PanacheMock.mock(Book.class);

    Book book1 = new Book();
    book1.id = 1L;
    book1.title = "Book One";
    book1.price = new BigDecimal("19.99");

    Book book2 = new Book();
    book2.id = 2L;
    book2.title = "Book Two";
    book2.price = new BigDecimal("24.99");

    Mockito.when(Book.listAll()).thenReturn(List.of(book1, book2));

    List<Book> books = Book.listAll();

    assertEquals(2, books.size());
    assertEquals("Book One", books.get(0).title);
    assertEquals("Book Two", books.get(1).title);

    PanacheMock.verify(Book.class, Mockito.times(1)).listAll();
  }

  @Test
  void shouldMockBookFindByLanguage() {
    PanacheMock.mock(Book.class);

    Book englishBook = new Book();
    englishBook.id = 1L;
    englishBook.title = "English Novel";
    englishBook.language = Language.ENGLISH;

    Mockito.when(Book.list("language", Language.ENGLISH)).thenReturn(List.of(englishBook));

    List<Book> englishBooks = Book.list("language", Language.ENGLISH);

    assertEquals(1, englishBooks.size());
    assertEquals("English Novel", englishBooks.get(0).title);
    assertEquals(Language.ENGLISH, englishBooks.get(0).language);

    PanacheMock.verify(Book.class, Mockito.times(1)).list("language", Language.ENGLISH);
  }

  @Test
  void shouldMockCDCount() {
    PanacheMock.mock(CD.class);

    Mockito.when(CD.count()).thenReturn(25L);

    assertEquals(25L, CD.count());

    PanacheMock.verify(CD.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockCDFindById() {
    PanacheMock.mock(CD.class);

    CD mockCD = new CD();
    mockCD.id = 1L;
    mockCD.title = "Mocked Album";
    mockCD.price = new BigDecimal("19.99");
    mockCD.stock = 50;
    mockCD.ean = "1234567890123";
    mockCD.musicCompany = "Mock Records";
    mockCD.genre = MusicGenre.ROCK;

    Mockito.when(CD.findById(1L)).thenReturn(mockCD);

    CD found = CD.findById(1L);

    assertNotNull(found);
    assertEquals("Mocked Album", found.title);
    assertEquals("Mock Records", found.musicCompany);
    assertEquals(MusicGenre.ROCK, found.genre);

    PanacheMock.verify(CD.class, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockCDFindByGenre() {
    PanacheMock.mock(CD.class);

    CD jazzCD = new CD();
    jazzCD.id = 1L;
    jazzCD.title = "Jazz Classics";
    jazzCD.genre = MusicGenre.JAZZ;

    CD jazzCD2 = new CD();
    jazzCD2.id = 2L;
    jazzCD2.title = "More Jazz";
    jazzCD2.genre = MusicGenre.JAZZ;

    Mockito.when(CD.list("genre", MusicGenre.JAZZ)).thenReturn(List.of(jazzCD, jazzCD2));

    List<CD> jazzCDs = CD.list("genre", MusicGenre.JAZZ);

    assertEquals(2, jazzCDs.size());
    assertTrue(jazzCDs.stream().allMatch(cd -> cd.genre == MusicGenre.JAZZ));

    PanacheMock.verify(CD.class, Mockito.times(1)).list("genre", MusicGenre.JAZZ);
  }

  @Test
  void shouldMockAuthorCount() {
    PanacheMock.mock(Author.class);

    Mockito.when(Author.count()).thenReturn(15L);

    assertEquals(15L, Author.count());

    PanacheMock.verify(Author.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockAuthorFindById() {
    PanacheMock.mock(Author.class);

    Author mockAuthor = new Author();
    mockAuthor.id = 1L;
    mockAuthor.firstName = "Jane";
    mockAuthor.lastName = "Doe";
    mockAuthor.bio = "Famous author";
    mockAuthor.preferredLanguage = Language.ENGLISH;

    Mockito.when(Author.findById(1L)).thenReturn(mockAuthor);

    Author found = Author.findById(1L);

    assertNotNull(found);
    assertEquals("Jane", found.firstName);
    assertEquals("Doe", found.lastName);
    assertEquals(Language.ENGLISH, found.preferredLanguage);

    PanacheMock.verify(Author.class, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockMusicianCount() {
    PanacheMock.mock(Musician.class);

    Mockito.when(Musician.count()).thenReturn(30L);

    assertEquals(30L, Musician.count());

    PanacheMock.verify(Musician.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockMusicianFindById() {
    PanacheMock.mock(Musician.class);

    Musician mockMusician = new Musician();
    mockMusician.id = 1L;
    mockMusician.firstName = "John";
    mockMusician.lastName = "Smith";
    mockMusician.stageName = "Johnny Rock";
    mockMusician.instrument = "Guitar";

    Mockito.when(Musician.findById(1L)).thenReturn(mockMusician);

    Musician found = Musician.findById(1L);

    assertNotNull(found);
    assertEquals("John", found.firstName);
    assertEquals("Johnny Rock", found.stageName);
    assertEquals("Guitar", found.instrument);

    PanacheMock.verify(Musician.class, Mockito.times(1)).findById(1L);
  }

  // ======================================
  // Repository Pattern Mocking (@InjectMock)
  // ======================================

  @Test
  void shouldMockUserRepositoryCount() {
    Mockito.when(userRepository.count()).thenReturn(100L);

    assertEquals(100L, userRepository.count());

    Mockito.verify(userRepository, Mockito.times(1)).count();
  }

  @Test
  void shouldMockUserRepositoryFindById() {
    User mockUser = new User();
    mockUser.setId(1L);
    mockUser.setUsername("testuser");
    mockUser.setEmail("test@example.com");
    mockUser.setRole(UserRole.CUSTOMER);
    mockUser.setEnabled(true);

    Mockito.when(userRepository.findById(1L)).thenReturn(mockUser);

    User found = userRepository.findById(1L);

    assertNotNull(found);
    assertEquals("testuser", found.getUsername());
    assertEquals("test@example.com", found.getEmail());
    assertEquals(UserRole.CUSTOMER, found.getRole());

    Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockUserRepositoryFindByUsername() {
    User mockUser = new User();
    mockUser.setId(1L);
    mockUser.setUsername("johndoe");
    mockUser.setEmail("john@example.com");
    mockUser.setRole(UserRole.ADMIN);

    Mockito.when(userRepository.findByUsername("johndoe")).thenReturn(mockUser);

    User found = userRepository.findByUsername("johndoe");

    assertNotNull(found);
    assertEquals("johndoe", found.getUsername());
    assertEquals(UserRole.ADMIN, found.getRole());

    Mockito.verify(userRepository, Mockito.times(1)).findByUsername("johndoe");
  }

  @Test
  void shouldMockUserRepositoryFindByUsernameNotFound() {
    Mockito.when(userRepository.findByUsername("nonexistent")).thenReturn(null);

    User found = userRepository.findByUsername("nonexistent");

    assertNull(found);

    Mockito.verify(userRepository, Mockito.times(1)).findByUsername("nonexistent");
  }

  @Test
  void shouldMockCustomerRepositoryCount() {
    Mockito.when(customerRepository.count()).thenReturn(50L);

    assertEquals(50L, customerRepository.count());

    Mockito.verify(customerRepository, Mockito.times(1)).count();
  }

  @Test
  void shouldMockCustomerRepositoryFindById() {
    Address billingAddress = new Address();
    billingAddress.setStreet("123 Main St");
    billingAddress.setCity("New York");
    billingAddress.setState("NY");
    billingAddress.setZipCode("10001");
    billingAddress.setCountry("USA");

    Customer mockCustomer = new Customer();
    mockCustomer.setId(1L);
    mockCustomer.setFirstName("John");
    mockCustomer.setLastName("Doe");
    mockCustomer.setPhone("555-1234");
    mockCustomer.setBillingAddress(billingAddress);

    Mockito.when(customerRepository.findById(1L)).thenReturn(mockCustomer);

    Customer found = customerRepository.findById(1L);

    assertNotNull(found);
    assertEquals("John", found.getFirstName());
    assertEquals("Doe", found.getLastName());
    assertNotNull(found.getBillingAddress());
    assertEquals("New York", found.getBillingAddress().getCity());

    Mockito.verify(customerRepository, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockCustomerRepositoryListAll() {
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstName("John");
    customer1.setLastName("Doe");

    Customer customer2 = new Customer();
    customer2.setId(2L);
    customer2.setFirstName("Jane");
    customer2.setLastName("Smith");

    Mockito.when(customerRepository.listAll()).thenReturn(List.of(customer1, customer2));

    List<Customer> customers = customerRepository.listAll();

    assertEquals(2, customers.size());
    assertEquals("John", customers.get(0).getFirstName());
    assertEquals("Jane", customers.get(1).getFirstName());

    Mockito.verify(customerRepository, Mockito.times(1)).listAll();
  }

  @Test
  void shouldMockPurchaseOrderRepositoryCount() {
    Mockito.when(purchaseOrderRepository.count()).thenReturn(200L);

    assertEquals(200L, purchaseOrderRepository.count());

    Mockito.verify(purchaseOrderRepository, Mockito.times(1)).count();
  }

  @Test
  void shouldMockPurchaseOrderRepositoryFindById() {
    Customer mockCustomer = new Customer();
    mockCustomer.setId(1L);
    mockCustomer.setFirstName("John");
    mockCustomer.setLastName("Doe");

    Address shippingAddress = new Address();
    shippingAddress.setStreet("456 Oak Ave");
    shippingAddress.setCity("Los Angeles");
    shippingAddress.setState("CA");
    shippingAddress.setZipCode("90001");
    shippingAddress.setCountry("USA");

    PurchaseOrder mockOrder = new PurchaseOrder();
    mockOrder.setId(1L);
    mockOrder.setOrderDate(LocalDateTime.of(2024, 1, 15, 10, 30));
    mockOrder.setStatus(OrderStatus.PENDING);
    mockOrder.setTotalAmount(new BigDecimal("99.99"));
    mockOrder.setCustomer(mockCustomer);
    mockOrder.setShippingAddress(shippingAddress);

    Mockito.when(purchaseOrderRepository.findById(1L)).thenReturn(mockOrder);

    PurchaseOrder found = purchaseOrderRepository.findById(1L);

    assertNotNull(found);
    assertEquals(OrderStatus.PENDING, found.getStatus());
    assertEquals(new BigDecimal("99.99"), found.getTotalAmount());
    assertNotNull(found.getCustomer());
    assertEquals("John", found.getCustomer().getFirstName());

    Mockito.verify(purchaseOrderRepository, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockPurchaseOrderRepositoryFindByStatus() {
    PurchaseOrder order1 = new PurchaseOrder();
    order1.setId(1L);
    order1.setStatus(OrderStatus.SHIPPED);
    order1.setTotalAmount(new BigDecimal("50.00"));

    PurchaseOrder order2 = new PurchaseOrder();
    order2.setId(2L);
    order2.setStatus(OrderStatus.SHIPPED);
    order2.setTotalAmount(new BigDecimal("75.00"));

    Mockito.when(purchaseOrderRepository.list("status", OrderStatus.SHIPPED))
        .thenReturn(List.of(order1, order2));

    List<PurchaseOrder> shippedOrders = purchaseOrderRepository.list("status", OrderStatus.SHIPPED);

    assertEquals(2, shippedOrders.size());
    assertTrue(shippedOrders.stream().allMatch(o -> o.getStatus() == OrderStatus.SHIPPED));

    Mockito.verify(purchaseOrderRepository, Mockito.times(1)).list("status", OrderStatus.SHIPPED);
  }

  @Test
  void shouldMockPurchaseOrderRepositoryFindByCustomer() {
    Customer mockCustomer = new Customer();
    mockCustomer.setId(1L);
    mockCustomer.setFirstName("John");

    PurchaseOrder order1 = new PurchaseOrder();
    order1.setId(1L);
    order1.setStatus(OrderStatus.DELIVERED);
    order1.setCustomer(mockCustomer);

    PurchaseOrder order2 = new PurchaseOrder();
    order2.setId(2L);
    order2.setStatus(OrderStatus.PENDING);
    order2.setCustomer(mockCustomer);

    Mockito.when(purchaseOrderRepository.list("customer", mockCustomer))
        .thenReturn(List.of(order1, order2));

    List<PurchaseOrder> customerOrders = purchaseOrderRepository.list("customer", mockCustomer);

    assertEquals(2, customerOrders.size());
    assertTrue(customerOrders.stream().allMatch(o -> o.getCustomer().getId().equals(1L)));

    Mockito.verify(purchaseOrderRepository, Mockito.times(1)).list("customer", mockCustomer);
  }

  // ======================================
  // Combined Mock Tests
  // ======================================

  @Test
  void shouldMockMultipleEntitiesSimultaneously() {
    PanacheMock.mock(Book.class);
    PanacheMock.mock(CD.class);

    Mockito.when(Book.count()).thenReturn(100L);
    Mockito.when(CD.count()).thenReturn(50L);

    assertEquals(100L, Book.count());
    assertEquals(50L, CD.count());
    assertEquals(150L, Book.count() + CD.count());

    PanacheMock.verify(Book.class, Mockito.times(2)).count();
    PanacheMock.verify(CD.class, Mockito.times(2)).count();
  }

  @Test
  void shouldMockDeleteOperation() {
    PanacheMock.mock(Book.class);

    Mockito.when(Book.deleteById(1L)).thenReturn(true);
    Mockito.when(Book.deleteById(999L)).thenReturn(false);

    assertTrue(Book.deleteById(1L));
    assertFalse(Book.deleteById(999L));

    PanacheMock.verify(Book.class, Mockito.times(1)).deleteById(1L);
    PanacheMock.verify(Book.class, Mockito.times(1)).deleteById(999L);
  }

  @Test
  void shouldVerifyNoMoreInteractions() {
    Mockito.when(userRepository.count()).thenReturn(10L);

    userRepository.count();

    Mockito.verify(userRepository).count();
    Mockito.verifyNoMoreInteractions(userRepository);
  }
}
