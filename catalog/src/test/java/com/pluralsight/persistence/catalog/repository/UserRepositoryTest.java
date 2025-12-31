package com.pluralsight.persistence.catalog.repository;

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
class UserRepositoryTest {

  @Inject
  UserRepository userRepository;

  @Inject
  CustomerRepository customerRepository;

  @Inject
  PurchaseOrderRepository purchaseOrderRepository;

  @Test
  @TestTransaction
  void shouldCreateUser() {
    String uniqueUsername = "user" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    userRepository.persist(user);

    assertNotNull(user.getId());
  }

  @Test
  @TestTransaction
  void shouldFindUserById() {
    String uniqueUsername = "find" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.ADMIN);
    user.setEnabled(true);
    userRepository.persist(user);

    User found = userRepository.findById(user.getId());

    assertNotNull(found);
    assertEquals(uniqueUsername, found.getUsername());
    assertEquals(UserRole.ADMIN, found.getRole());
  }

  @Test
  @TestTransaction
  void shouldNotFindUserById() {
    User found = userRepository.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeleteUser() {
    String uniqueUsername = "del" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    Long userId = user.getId();
    assertNotNull(userRepository.findById(userId));

    boolean deleted = userRepository.deleteById(userId);

    assertTrue(deleted);
    assertNull(userRepository.findById(userId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingUser() {
    boolean deleted = userRepository.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountUsers() {
    long initialCount = userRepository.count();

    String uniqueUsername = "cnt" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    assertEquals(initialCount + 1, userRepository.count());
  }

  @Test
  @TestTransaction
  void shouldListAllUsers() {
    long initialCount = userRepository.count();

    String uniqueUsername1 = "list1" + UUID.randomUUID().toString().substring(0, 8);
    User user1 = new User();
    user1.setUsername(uniqueUsername1);
    user1.setPassword("password123");
    user1.setEmail(uniqueUsername1 + "@example.com");
    user1.setRole(UserRole.CUSTOMER);
    user1.setEnabled(true);
    userRepository.persist(user1);

    String uniqueUsername2 = "list2" + UUID.randomUUID().toString().substring(0, 8);
    User user2 = new User();
    user2.setUsername(uniqueUsername2);
    user2.setPassword("password456");
    user2.setEmail(uniqueUsername2 + "@example.com");
    user2.setRole(UserRole.ADMIN);
    user2.setEnabled(true);
    userRepository.persist(user2);

    List<User> users = userRepository.listAll();

    assertEquals(initialCount + 2, users.size());
  }

  @Test
  @TestTransaction
  void shouldDeleteAllUsers() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    String uniqueUsername1 = "del1" + UUID.randomUUID().toString().substring(0, 8);
    User user1 = new User();
    user1.setUsername(uniqueUsername1);
    user1.setPassword("password123");
    user1.setEmail(uniqueUsername1 + "@example.com");
    user1.setRole(UserRole.CUSTOMER);
    user1.setEnabled(true);
    userRepository.persist(user1);

    String uniqueUsername2 = "del2" + UUID.randomUUID().toString().substring(0, 8);
    User user2 = new User();
    user2.setUsername(uniqueUsername2);
    user2.setPassword("password456");
    user2.setEmail(uniqueUsername2 + "@example.com");
    user2.setRole(UserRole.ADMIN);
    user2.setEnabled(true);
    userRepository.persist(user2);

    assertEquals(2, userRepository.count());

    userRepository.deleteAll();

    assertEquals(0, userRepository.count());
  }

  @Test
  @TestTransaction
  void shouldFindUserByUsername() {
    String uniqueUsername = "byname" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    User found = userRepository.findByUsername(uniqueUsername);

    assertNotNull(found);
    assertEquals(uniqueUsername, found.getUsername());
  }

  @Test
  @TestTransaction
  void shouldNotFindUserByUsername() {
    User found = userRepository.findByUsername("nonexistent_user_xyz");
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldFindUserByEmail() {
    String uniqueUsername = "email" + UUID.randomUUID().toString().substring(0, 8);
    String uniqueEmail = uniqueUsername + "@test.com";
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueEmail);
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    User found = userRepository.findByEmail(uniqueEmail);

    assertNotNull(found);
    assertEquals(uniqueEmail, found.getEmail());
  }

  @Test
  @TestTransaction
  void shouldNotFindUserByEmail() {
    User found = userRepository.findByEmail("nonexistent@email.xyz");
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldFindUserByRole() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    String username1 = "role1" + UUID.randomUUID().toString().substring(0, 8);
    User customer1 = new User();
    customer1.setUsername(username1);
    customer1.setPassword("password123");
    customer1.setEmail(username1 + "@example.com");
    customer1.setRole(UserRole.CUSTOMER);
    customer1.setEnabled(true);
    userRepository.persist(customer1);

    String username2 = "role2" + UUID.randomUUID().toString().substring(0, 8);
    User customer2 = new User();
    customer2.setUsername(username2);
    customer2.setPassword("password456");
    customer2.setEmail(username2 + "@example.com");
    customer2.setRole(UserRole.CUSTOMER);
    customer2.setEnabled(true);
    userRepository.persist(customer2);

    String username3 = "role3" + UUID.randomUUID().toString().substring(0, 8);
    User admin = new User();
    admin.setUsername(username3);
    admin.setPassword("password789");
    admin.setEmail(username3 + "@example.com");
    admin.setRole(UserRole.ADMIN);
    admin.setEnabled(true);
    userRepository.persist(admin);

    List<User> customers = userRepository.findByRole(UserRole.CUSTOMER);
    List<User> admins = userRepository.findByRole(UserRole.ADMIN);

    assertEquals(2, customers.size());
    assertEquals(1, admins.size());
    assertTrue(customers.stream().allMatch(u -> u.getRole() == UserRole.CUSTOMER));
    assertTrue(admins.stream().allMatch(u -> u.getRole() == UserRole.ADMIN));
  }

  @Test
  @TestTransaction
  void shouldNotFindUserByRole() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    String username = "norole" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(username);
    user.setPassword("password123");
    user.setEmail(username + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    List<User> admins = userRepository.findByRole(UserRole.ADMIN);

    assertTrue(admins.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindUserByEmailContaining() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    String username1 = "email1" + UUID.randomUUID().toString().substring(0, 8);
    User user1 = new User();
    user1.setUsername(username1);
    user1.setPassword("password123");
    user1.setEmail(username1 + "@gmail.com");
    user1.setRole(UserRole.CUSTOMER);
    user1.setEnabled(true);
    userRepository.persist(user1);

    String username2 = "email2" + UUID.randomUUID().toString().substring(0, 8);
    User user2 = new User();
    user2.setUsername(username2);
    user2.setPassword("password456");
    user2.setEmail(username2 + "@gmail.com");
    user2.setRole(UserRole.CUSTOMER);
    user2.setEnabled(true);
    userRepository.persist(user2);

    String username3 = "email3" + UUID.randomUUID().toString().substring(0, 8);
    User user3 = new User();
    user3.setUsername(username3);
    user3.setPassword("password789");
    user3.setEmail(username3 + "@yahoo.com");
    user3.setRole(UserRole.CUSTOMER);
    user3.setEnabled(true);
    userRepository.persist(user3);

    List<User> gmailUsers = userRepository.findByEmailContaining("gmail");
    List<User> yahooUsers = userRepository.findByEmailContaining("yahoo");

    assertEquals(2, gmailUsers.size());
    assertEquals(1, yahooUsers.size());
    assertTrue(gmailUsers.stream().allMatch(u -> u.getEmail().contains("gmail")));
  }

  @Test
  @TestTransaction
  void shouldNotFindUserByEmailContaining() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    String username = "noemail" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(username);
    user.setPassword("password123");
    user.setEmail(username + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);
    userRepository.persist(user);

    List<User> users = userRepository.findByEmailContaining("nonexistent");

    assertTrue(users.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldCountByRole() {
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    String username1 = "cnt1" + UUID.randomUUID().toString().substring(0, 8);
    User customer1 = new User();
    customer1.setUsername(username1);
    customer1.setPassword("password123");
    customer1.setEmail(username1 + "@example.com");
    customer1.setRole(UserRole.CUSTOMER);
    customer1.setEnabled(true);
    userRepository.persist(customer1);

    String username2 = "cnt2" + UUID.randomUUID().toString().substring(0, 8);
    User customer2 = new User();
    customer2.setUsername(username2);
    customer2.setPassword("password456");
    customer2.setEmail(username2 + "@example.com");
    customer2.setRole(UserRole.CUSTOMER);
    customer2.setEnabled(true);
    userRepository.persist(customer2);

    String username3 = "cnt3" + UUID.randomUUID().toString().substring(0, 8);
    User admin = new User();
    admin.setUsername(username3);
    admin.setPassword("password789");
    admin.setEmail(username3 + "@example.com");
    admin.setRole(UserRole.ADMIN);
    admin.setEnabled(true);
    userRepository.persist(admin);

    long customerCount = userRepository.countByRole(UserRole.CUSTOMER);
    long adminCount = userRepository.countByRole(UserRole.ADMIN);

    assertEquals(2, customerCount);
    assertEquals(1, adminCount);
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnUser() {
    // Clear all dependent entities first (FK constraints)
    purchaseOrderRepository.deleteAll();
    customerRepository.deleteAll();
    userRepository.deleteAll();

    // Find all and check there is zero
    assertEquals(0, userRepository.count());
    assertTrue(userRepository.listAll().isEmpty());

    // Create two items
    String username1 = "crud1" + UUID.randomUUID().toString().substring(0, 8);
    User user1 = new User();
    user1.setUsername(username1);
    user1.setPassword("password123");
    user1.setEmail(username1 + "@example.com");
    user1.setRole(UserRole.CUSTOMER);
    user1.setEnabled(true);
    userRepository.persist(user1);

    String username2 = "crud2" + UUID.randomUUID().toString().substring(0, 8);
    User user2 = new User();
    user2.setUsername(username2);
    user2.setPassword("password456");
    user2.setEmail(username2 + "@example.com");
    user2.setRole(UserRole.ADMIN);
    user2.setEnabled(true);
    userRepository.persist(user2);

    // Find all and check there is two
    assertEquals(2, userRepository.count());
    assertEquals(2, userRepository.listAll().size());

    // Find by id and check it's the right one
    User foundUser1 = userRepository.findById(user1.getId());
    assertNotNull(foundUser1);
    assertEquals(username1, foundUser1.getUsername());
    assertEquals(UserRole.CUSTOMER, foundUser1.getRole());

    // Update one item, find by id and check it's ok
    foundUser1.setRole(UserRole.ADMIN);
    foundUser1.setEnabled(false);
    User updatedUser = userRepository.findById(user1.getId());
    assertEquals(UserRole.ADMIN, updatedUser.getRole());
    assertEquals(false, updatedUser.getEnabled());

    // Delete one item and check there is only one now
    userRepository.deleteById(user1.getId());
    assertEquals(1, userRepository.count());
    assertNull(userRepository.findById(user1.getId()));
    assertNotNull(userRepository.findById(user2.getId()));

    // Delete the second item and check findall returns zero
    userRepository.deleteById(user2.getId());
    assertEquals(0, userRepository.count());
    assertTrue(userRepository.listAll().isEmpty());
  }
}
