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
