package com.pluralsight.persistence.catalog.model;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
class PublisherTest {

  @Test
  @TestTransaction
  void shouldCreatePublisher() {
    Publisher publisher = new Publisher();
    publisher.name = "Test Publisher";
    publisher.country = "USA";
    publisher.website = "https://test-publisher.com";
    publisher.foundedYear = 2000;

    publisher.persist();

    assertNotNull(publisher.id);
    assertNotNull(publisher.createdDate);
  }

  @Test
  @TestTransaction
  void shouldFindPublisherById() {
    Publisher publisher = new Publisher();
    publisher.name = "Findable Publisher";
    publisher.country = "UK";
    publisher.website = "https://findable.com";
    publisher.foundedYear = 1990;
    publisher.persist();

    Publisher found = Publisher.findById(publisher.id);

    assertNotNull(found);
    assertEquals("Findable Publisher", found.name);
    assertEquals("UK", found.country);
    assertEquals(1990, found.foundedYear);
  }

  @Test
  @TestTransaction
  void shouldNotFindPublisherById() {
    Publisher found = Publisher.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeletePublisher() {
    Publisher publisher = new Publisher();
    publisher.name = "To Be Deleted Publisher";
    publisher.country = "France";
    publisher.persist();

    Long publisherId = publisher.id;
    assertNotNull(Publisher.findById(publisherId));

    boolean deleted = Publisher.deleteById(publisherId);

    assertTrue(deleted);
    assertNull(Publisher.findById(publisherId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingPublisher() {
    boolean deleted = Publisher.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountPublishers() {
    long initialCount = Publisher.count();

    Publisher publisher = new Publisher();
    publisher.name = "Count Test Publisher";
    publisher.country = "Germany";
    publisher.persist();

    assertEquals(initialCount + 1, Publisher.count());
  }

  @Test
  @TestTransaction
  void shouldListAllPublishers() {
    long initialCount = Publisher.count();

    Publisher publisher1 = new Publisher();
    publisher1.name = "Publisher One";
    publisher1.country = "USA";
    publisher1.persist();

    Publisher publisher2 = new Publisher();
    publisher2.name = "Publisher Two";
    publisher2.country = "UK";
    publisher2.persist();

    List<Publisher> publishers = Publisher.listAll();

    assertEquals(initialCount + 2, publishers.size());
  }

  @Test
  @TestTransaction
  void shouldDeleteAllPublishers() {
    Book.deleteAll();
    Publisher.deleteAll();

    Publisher publisher1 = new Publisher();
    publisher1.name = "Publisher One";
    publisher1.country = "USA";
    publisher1.persist();

    Publisher publisher2 = new Publisher();
    publisher2.name = "Publisher Two";
    publisher2.country = "UK";
    publisher2.persist();

    assertEquals(2, Publisher.count());

    Publisher.deleteAll();

    assertEquals(0, Publisher.count());
  }

  @Test
  @TestTransaction
  void shouldFindPublisherByNameContaining() {
    Book.deleteAll();
    Publisher.deleteAll();

    Publisher publisher1 = new Publisher();
    publisher1.name = "Random House";
    publisher1.country = "USA";
    publisher1.persist();

    Publisher publisher2 = new Publisher();
    publisher2.name = "Penguin Books";
    publisher2.country = "UK";
    publisher2.persist();

    Publisher publisher3 = new Publisher();
    publisher3.name = "HarperCollins";
    publisher3.country = "USA";
    publisher3.persist();

    List<Publisher> publishers = Publisher.findByNameContaining("house");

    assertEquals(1, publishers.size());
    assertEquals("Random House", publishers.get(0).name);
  }

  @Test
  @TestTransaction
  void shouldNotFindPublisherByNameContaining() {
    Book.deleteAll();
    Publisher.deleteAll();

    Publisher publisher = new Publisher();
    publisher.name = "Test Publisher";
    publisher.country = "USA";
    publisher.persist();

    List<Publisher> publishers = Publisher.findByNameContaining("Scholastic");

    assertTrue(publishers.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindPublisherByCountry() {
    Book.deleteAll();
    Publisher.deleteAll();

    Publisher usPublisher1 = new Publisher();
    usPublisher1.name = "US Publisher One";
    usPublisher1.country = "USA";
    usPublisher1.persist();

    Publisher usPublisher2 = new Publisher();
    usPublisher2.name = "US Publisher Two";
    usPublisher2.country = "USA";
    usPublisher2.persist();

    Publisher ukPublisher = new Publisher();
    ukPublisher.name = "UK Publisher";
    ukPublisher.country = "UK";
    ukPublisher.persist();

    List<Publisher> usPublishers = Publisher.findByCountry("USA");

    assertEquals(2, usPublishers.size());
    assertTrue(usPublishers.stream().allMatch(p -> "USA".equals(p.country)));
  }

  @Test
  @TestTransaction
  void shouldNotFindPublisherByCountry() {
    Book.deleteAll();
    Publisher.deleteAll();

    Publisher publisher = new Publisher();
    publisher.name = "Test Publisher";
    publisher.country = "USA";
    publisher.persist();

    List<Publisher> publishers = Publisher.findByCountry("Japan");

    assertTrue(publishers.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnPublisher() {
    // Clear all books and publishers
    Book.deleteAll();
    Publisher.deleteAll();

    // Find all and check there is zero
    assertEquals(0, Publisher.count());
    assertTrue(Publisher.listAll().isEmpty());

    // Create two items
    Publisher publisher1 = new Publisher();
    publisher1.name = "First Publisher";
    publisher1.country = "USA";
    publisher1.website = "https://first.com";
    publisher1.foundedYear = 1980;
    publisher1.persist();

    Publisher publisher2 = new Publisher();
    publisher2.name = "Second Publisher";
    publisher2.country = "UK";
    publisher2.website = "https://second.com";
    publisher2.foundedYear = 1995;
    publisher2.persist();

    // Find all and check there is two
    assertEquals(2, Publisher.count());
    assertEquals(2, Publisher.listAll().size());

    // Find by id and check it's the right one
    Publisher foundPublisher1 = Publisher.findById(publisher1.id);
    assertNotNull(foundPublisher1);
    assertEquals("First Publisher", foundPublisher1.name);
    assertEquals("USA", foundPublisher1.country);
    assertEquals(1980, foundPublisher1.foundedYear);

    // Update one item, find by id and check it's ok
    foundPublisher1.name = "Updated First Publisher";
    foundPublisher1.foundedYear = 1985;
    Publisher updatedPublisher = Publisher.findById(publisher1.id);
    assertEquals("Updated First Publisher", updatedPublisher.name);
    assertEquals(1985, updatedPublisher.foundedYear);

    // Delete one item and check there is only one now
    Publisher.deleteById(publisher1.id);
    assertEquals(1, Publisher.count());
    assertNull(Publisher.findById(publisher1.id));
    assertNotNull(Publisher.findById(publisher2.id));

    // Delete the second item and check findall returns zero
    Publisher.deleteById(publisher2.id);
    assertEquals(0, Publisher.count());
    assertTrue(Publisher.listAll().isEmpty());
  }
}
