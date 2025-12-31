package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.customer.model.Address;
import com.pluralsight.persistence.customer.model.Customer;
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

import java.util.List;
import java.util.UUID;

@QuarkusTest
class CustomerRepositoryTest {

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

  @Test
  @TestTransaction
  void shouldCreateCustomer() {
    User user = createUser();

    Customer customer = new Customer();
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setPhone("555-1234");
    customer.setBillingAddress(createAddress());
    customer.setUser(user);

    customerRepository.persist(customer);

    assertNotNull(customer.getId());
  }

  @Test
  @TestTransaction
  void shouldFindCustomerById() {
    User user = createUser();

    Customer customer = new Customer();
    customer.setFirstName("Jane");
    customer.setLastName("Smith");
    customer.setPhone("555-5678");
    customer.setBillingAddress(createAddress());
    customer.setUser(user);
    customerRepository.persist(customer);

    Customer found = customerRepository.findById(customer.getId());

    assertNotNull(found);
    assertEquals("Jane", found.getFirstName());
    assertEquals("Smith", found.getLastName());
  }

  @Test
  @TestTransaction
  void shouldNotFindCustomerById() {
    Customer found = customerRepository.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeleteCustomer() {
    User user = createUser();

    Customer customer = new Customer();
    customer.setFirstName("Delete");
    customer.setLastName("Me");
    customer.setPhone("555-0000");
    customer.setBillingAddress(createAddress());
    customer.setUser(user);
    customerRepository.persist(customer);

    Long customerId = customer.getId();
    assertNotNull(customerRepository.findById(customerId));

    boolean deleted = customerRepository.deleteById(customerId);

    assertTrue(deleted);
    assertNull(customerRepository.findById(customerId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingCustomer() {
    boolean deleted = customerRepository.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountCustomers() {
    long initialCount = customerRepository.count();

    User user = createUser();
    Customer customer = new Customer();
    customer.setFirstName("Count");
    customer.setLastName("Test");
    customer.setPhone("555-1234");
    customer.setBillingAddress(createAddress());
    customer.setUser(user);
    customerRepository.persist(customer);

    assertEquals(initialCount + 1, customerRepository.count());
  }

  @Test
  @TestTransaction
  void shouldListAllCustomers() {
    long initialCount = customerRepository.count();

    User user1 = createUser();
    Customer customer1 = new Customer();
    customer1.setFirstName("Customer");
    customer1.setLastName("One");
    customer1.setPhone("555-1111");
    customer1.setBillingAddress(createAddress());
    customer1.setUser(user1);
    customerRepository.persist(customer1);

    User user2 = createUser();
    Customer customer2 = new Customer();
    customer2.setFirstName("Customer");
    customer2.setLastName("Two");
    customer2.setPhone("555-2222");
    customer2.setBillingAddress(createAddress());
    customer2.setUser(user2);
    customerRepository.persist(customer2);

    List<Customer> customers = customerRepository.listAll();

    assertEquals(initialCount + 2, customers.size());
  }

  @Test
  @TestTransaction
  void shouldDeleteAllCustomers() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    User user1 = createUser();
    Customer customer1 = new Customer();
    customer1.setFirstName("Customer");
    customer1.setLastName("One");
    customer1.setPhone("555-1111");
    customer1.setBillingAddress(createAddress());
    customer1.setUser(user1);
    customerRepository.persist(customer1);

    User user2 = createUser();
    Customer customer2 = new Customer();
    customer2.setFirstName("Customer");
    customer2.setLastName("Two");
    customer2.setPhone("555-2222");
    customer2.setBillingAddress(createAddress());
    customer2.setUser(user2);
    customerRepository.persist(customer2);

    assertEquals(2, customerRepository.count());

    customerRepository.deleteAll();

    assertEquals(0, customerRepository.count());
  }

  @Test
  @TestTransaction
  void shouldFindCustomerByLastName() {
    User user1 = createUser();
    Customer customer1 = new Customer();
    customer1.setFirstName("John");
    customer1.setLastName("TestLastName");
    customer1.setPhone("555-1111");
    customer1.setBillingAddress(createAddress());
    customer1.setUser(user1);
    customerRepository.persist(customer1);

    User user2 = createUser();
    Customer customer2 = new Customer();
    customer2.setFirstName("Jane");
    customer2.setLastName("TestLastName");
    customer2.setPhone("555-2222");
    customer2.setBillingAddress(createAddress());
    customer2.setUser(user2);
    customerRepository.persist(customer2);

    List<Customer> customers = customerRepository.findByLastName("TestLastName");

    assertTrue(customers.size() >= 2);
    assertTrue(customers.stream().allMatch(c -> c.getLastName().equals("TestLastName")));
  }

  @Test
  @TestTransaction
  void shouldNotFindCustomerByLastName() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();

    List<Customer> customers = customerRepository.findByLastName("NonExistentLastName");

    assertTrue(customers.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindCustomerByUserId() {
    User user = createUser();

    Customer customer = new Customer();
    customer.setFirstName("Bob");
    customer.setLastName("Builder");
    customer.setPhone("555-9999");
    customer.setBillingAddress(createAddress());
    customer.setUser(user);
    customerRepository.persist(customer);

    Customer found = customerRepository.findByUserId(user.getId());

    assertNotNull(found);
    assertEquals("Bob", found.getFirstName());
    assertEquals(user.getId(), found.getUser().getId());
  }

  @Test
  @TestTransaction
  void shouldNotFindCustomerByUserId() {
    Customer found = customerRepository.findByUserId(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnCustomer() {
    // Clear all dependent entities first (FK constraints)
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    // Find all and check there is zero
    assertEquals(0, customerRepository.count());
    assertTrue(customerRepository.listAll().isEmpty());

    // Create two items
    User user1 = createUser();
    Customer customer1 = new Customer();
    customer1.setFirstName("First");
    customer1.setLastName("Customer");
    customer1.setPhone("555-1111");
    customer1.setBillingAddress(createAddress());
    customer1.setUser(user1);
    customerRepository.persist(customer1);

    User user2 = createUser();
    Customer customer2 = new Customer();
    customer2.setFirstName("Second");
    customer2.setLastName("Customer");
    customer2.setPhone("555-2222");
    customer2.setBillingAddress(createAddress());
    customer2.setUser(user2);
    customerRepository.persist(customer2);

    // Find all and check there is two
    assertEquals(2, customerRepository.count());
    assertEquals(2, customerRepository.listAll().size());

    // Find by id and check it's the right one
    Customer foundCustomer1 = customerRepository.findById(customer1.getId());
    assertNotNull(foundCustomer1);
    assertEquals("First", foundCustomer1.getFirstName());
    assertEquals("Customer", foundCustomer1.getLastName());

    // Update one item, find by id and check it's ok
    foundCustomer1.setFirstName("Updated First");
    foundCustomer1.setPhone("555-9999");
    Customer updatedCustomer = customerRepository.findById(customer1.getId());
    assertEquals("Updated First", updatedCustomer.getFirstName());
    assertEquals("555-9999", updatedCustomer.getPhone());

    // Delete one item and check there is only one now
    customerRepository.deleteById(customer1.getId());
    assertEquals(1, customerRepository.count());
    assertNull(customerRepository.findById(customer1.getId()));
    assertNotNull(customerRepository.findById(customer2.getId()));

    // Delete the second item and check findall returns zero
    customerRepository.deleteById(customer2.getId());
    assertEquals(0, customerRepository.count());
    assertTrue(customerRepository.listAll().isEmpty());
  }
}
