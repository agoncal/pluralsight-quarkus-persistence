package com.pluralsight.persistence.catalog.repository;

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class PurchaseOrderRepositoryTest {

  @Inject
  UserRepository userRepository;

  @Inject
  CustomerRepository customerRepository;

  @Inject
  PurchaseOrderRepository purchaseOrderRepository;

  private User createUser() {
    String uniqueUsername = "user" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);
    return user;
  }

  private Address createAddress() {
    Address address = new Address();
    address.setStreet("123 Main St");
    address.setCity("New York");
    address.setState("NY");
    address.setZipCode("10001");
    address.setCountry("USA");
    return address;
  }

  private Customer createCustomer(User user) {
    Customer customer = new Customer();
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setPhone("555-1234");
    customer.setBillingAddress(createAddress());
    customer.setUser(user);
    customerRepository.persist(customer);
    return customer;
  }

  private PurchaseOrder createPurchaseOrder(Customer customer, OrderStatus status, BigDecimal amount) {
    PurchaseOrder order = new PurchaseOrder();
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(status);
    order.setTotalAmount(amount);
    order.setCustomer(customer);
    order.setShippingAddress(createAddress());
    return order;
  }

  @Test
  @TestTransaction
  void shouldCreatePurchaseOrder() {
    User user = createUser();
    Customer customer = createCustomer(user);
    PurchaseOrder order = createPurchaseOrder(customer, OrderStatus.PENDING, new BigDecimal("99.99"));

    purchaseOrderRepository.persist(order);

    assertNotNull(order.getId());
  }

  @Test
  @TestTransaction
  void shouldFindPurchaseOrderById() {
    User user = createUser();
    Customer customer = createCustomer(user);
    PurchaseOrder order = createPurchaseOrder(customer, OrderStatus.CONFIRMED, new BigDecimal("149.99"));
    purchaseOrderRepository.persist(order);

    PurchaseOrder found = purchaseOrderRepository.findById(order.getId());

    assertNotNull(found);
    assertEquals(OrderStatus.CONFIRMED, found.getStatus());
    assertEquals(new BigDecimal("149.99"), found.getTotalAmount());
  }

  @Test
  @TestTransaction
  void shouldNotFindPurchaseOrderById() {
    PurchaseOrder found = purchaseOrderRepository.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeletePurchaseOrder() {
    User user = createUser();
    Customer customer = createCustomer(user);
    PurchaseOrder order = createPurchaseOrder(customer, OrderStatus.CANCELLED, new BigDecimal("25.00"));
    purchaseOrderRepository.persist(order);

    Long orderId = order.getId();
    assertNotNull(purchaseOrderRepository.findById(orderId));

    boolean deleted = purchaseOrderRepository.deleteById(orderId);

    assertTrue(deleted);
    assertNull(purchaseOrderRepository.findById(orderId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingPurchaseOrder() {
    boolean deleted = purchaseOrderRepository.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountPurchaseOrders() {
    long initialCount = purchaseOrderRepository.count();

    User user = createUser();
    Customer customer = createCustomer(user);
    PurchaseOrder order = createPurchaseOrder(customer, OrderStatus.PENDING, new BigDecimal("50.00"));
    purchaseOrderRepository.persist(order);

    assertEquals(initialCount + 1, purchaseOrderRepository.count());
  }

  @Test
  @TestTransaction
  void shouldListAllPurchaseOrders() {
    long initialCount = purchaseOrderRepository.count();

    User user1 = createUser();
    Customer customer1 = createCustomer(user1);
    PurchaseOrder order1 = createPurchaseOrder(customer1, OrderStatus.PENDING, new BigDecimal("50.00"));
    purchaseOrderRepository.persist(order1);

    User user2 = createUser();
    Customer customer2 = createCustomer(user2);
    PurchaseOrder order2 = createPurchaseOrder(customer2, OrderStatus.CONFIRMED, new BigDecimal("75.00"));
    purchaseOrderRepository.persist(order2);

    List<PurchaseOrder> orders = purchaseOrderRepository.listAll();

    assertEquals(initialCount + 2, orders.size());
  }

  @Test
  @TestTransaction
  void shouldDeleteAllPurchaseOrders() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    User user1 = createUser();
    Customer customer1 = createCustomer(user1);
    PurchaseOrder order1 = createPurchaseOrder(customer1, OrderStatus.PENDING, new BigDecimal("50.00"));
    purchaseOrderRepository.persist(order1);

    User user2 = createUser();
    Customer customer2 = createCustomer(user2);
    PurchaseOrder order2 = createPurchaseOrder(customer2, OrderStatus.CONFIRMED, new BigDecimal("75.00"));
    purchaseOrderRepository.persist(order2);

    assertEquals(2, purchaseOrderRepository.count());

    purchaseOrderRepository.deleteAll();

    assertEquals(0, purchaseOrderRepository.count());
  }

  @Test
  @TestTransaction
  void shouldFindPurchaseOrderByCustomerId() {
    User user = createUser();
    Customer customer = createCustomer(user);

    PurchaseOrder order1 = createPurchaseOrder(customer, OrderStatus.PENDING, new BigDecimal("50.00"));
    purchaseOrderRepository.persist(order1);

    PurchaseOrder order2 = createPurchaseOrder(customer, OrderStatus.SHIPPED, new BigDecimal("75.00"));
    purchaseOrderRepository.persist(order2);

    List<PurchaseOrder> orders = purchaseOrderRepository.findByCustomerId(customer.getId());

    assertEquals(2, orders.size());
    assertTrue(orders.stream().allMatch(o -> o.getCustomer().getId().equals(customer.getId())));
  }

  @Test
  @TestTransaction
  void shouldNotFindPurchaseOrderByCustomerId() {
    List<PurchaseOrder> orders = purchaseOrderRepository.findByCustomerId(999999L);
    assertTrue(orders.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindPurchaseOrderByStatus() {
    User user1 = createUser();
    Customer customer1 = createCustomer(user1);
    PurchaseOrder order1 = createPurchaseOrder(customer1, OrderStatus.DELIVERED, new BigDecimal("100.00"));
    purchaseOrderRepository.persist(order1);

    User user2 = createUser();
    Customer customer2 = createCustomer(user2);
    PurchaseOrder order2 = createPurchaseOrder(customer2, OrderStatus.DELIVERED, new BigDecimal("200.00"));
    purchaseOrderRepository.persist(order2);

    List<PurchaseOrder> deliveredOrders = purchaseOrderRepository.findByStatus(OrderStatus.DELIVERED);

    assertTrue(deliveredOrders.size() >= 2);
    assertTrue(deliveredOrders.stream().allMatch(o -> o.getStatus() == OrderStatus.DELIVERED));
  }

  @Test
  @TestTransaction
  void shouldNotFindPurchaseOrderByStatus() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    List<PurchaseOrder> orders = purchaseOrderRepository.findByStatus(OrderStatus.DELIVERED);

    assertTrue(orders.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindPurchaseOrderByUsername() {
    User user = createUser();
    Customer customer = createCustomer(user);

    PurchaseOrder order1 = createPurchaseOrder(customer, OrderStatus.PENDING, new BigDecimal("50.00"));
    purchaseOrderRepository.persist(order1);

    PurchaseOrder order2 = createPurchaseOrder(customer, OrderStatus.CONFIRMED, new BigDecimal("75.00"));
    purchaseOrderRepository.persist(order2);

    List<PurchaseOrder> orders = purchaseOrderRepository.findByUsername(user.getUsername());

    assertEquals(2, orders.size());
    assertTrue(orders.stream().allMatch(o -> o.getCustomer().getUser().getUsername().equals(user.getUsername())));
  }

  @Test
  @TestTransaction
  void shouldNotFindPurchaseOrderByUsername() {
    List<PurchaseOrder> orders = purchaseOrderRepository.findByUsername("nonexistent_user_xyz");
    assertTrue(orders.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnPurchaseOrder() {
    // Clear all orders, customers, users
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    // Find all and check there is zero
    assertEquals(0, purchaseOrderRepository.count());
    assertTrue(purchaseOrderRepository.listAll().isEmpty());

    // Create two items
    User user1 = createUser();
    Customer customer1 = createCustomer(user1);
    PurchaseOrder order1 = createPurchaseOrder(customer1, OrderStatus.PENDING, new BigDecimal("100.00"));
    purchaseOrderRepository.persist(order1);

    User user2 = createUser();
    Customer customer2 = createCustomer(user2);
    PurchaseOrder order2 = createPurchaseOrder(customer2, OrderStatus.CONFIRMED, new BigDecimal("200.00"));
    purchaseOrderRepository.persist(order2);

    // Find all and check there is two
    assertEquals(2, purchaseOrderRepository.count());
    assertEquals(2, purchaseOrderRepository.listAll().size());

    // Find by id and check it's the right one
    PurchaseOrder foundOrder1 = purchaseOrderRepository.findById(order1.getId());
    assertNotNull(foundOrder1);
    assertEquals(OrderStatus.PENDING, foundOrder1.getStatus());
    assertEquals(new BigDecimal("100.00"), foundOrder1.getTotalAmount());

    // Update one item, find by id and check it's ok
    foundOrder1.setStatus(OrderStatus.SHIPPED);
    foundOrder1.setTotalAmount(new BigDecimal("110.00"));
    PurchaseOrder updatedOrder = purchaseOrderRepository.findById(order1.getId());
    assertEquals(OrderStatus.SHIPPED, updatedOrder.getStatus());
    assertEquals(new BigDecimal("110.00"), updatedOrder.getTotalAmount());

    // Delete one item and check there is only one now
    purchaseOrderRepository.deleteById(order1.getId());
    assertEquals(1, purchaseOrderRepository.count());
    assertNull(purchaseOrderRepository.findById(order1.getId()));
    assertNotNull(purchaseOrderRepository.findById(order2.getId()));

    // Delete the second item and check findall returns zero
    purchaseOrderRepository.deleteById(order2.getId());
    assertEquals(0, purchaseOrderRepository.count());
    assertTrue(purchaseOrderRepository.listAll().isEmpty());
  }
}
