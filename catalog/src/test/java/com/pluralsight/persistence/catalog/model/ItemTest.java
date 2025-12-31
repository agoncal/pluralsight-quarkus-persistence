package com.pluralsight.persistence.catalog.model;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@QuarkusTest
class ItemTest {

  // Testing Item methods using Book (since Item is abstract)

  @Test
  @TestTransaction
  void shouldFindByTitleContaining() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Book book1 = new Book();
    book1.title = "The Great Gatsby";
    book1.price = new BigDecimal("19.99");
    book1.stock = 10;
    book1.persist();

    Book book2 = new Book();
    book2.title = "Great Expectations";
    book2.price = new BigDecimal("14.99");
    book2.stock = 5;
    book2.persist();

    Book book3 = new Book();
    book3.title = "To Kill a Mockingbird";
    book3.price = new BigDecimal("12.99");
    book3.stock = 8;
    book3.persist();

    List<Item> items = Item.findByTitleContaining("great");

    assertEquals(2, items.size());
    assertTrue(items.stream().allMatch(i -> i.title.toLowerCase().contains("great")));
  }

  @Test
  @TestTransaction
  void shouldNotFindByTitleContaining() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Book book = new Book();
    book.title = "Test Book";
    book.price = new BigDecimal("10.00");
    book.stock = 10;
    book.persist();

    List<Item> items = Item.findByTitleContaining("nonexistent");

    assertTrue(items.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindByPriceRange() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Book book1 = new Book();
    book1.title = "Cheap Book";
    book1.price = new BigDecimal("5.00");
    book1.stock = 10;
    book1.persist();

    Book book2 = new Book();
    book2.title = "Medium Book";
    book2.price = new BigDecimal("15.00");
    book2.stock = 5;
    book2.persist();

    Book book3 = new Book();
    book3.title = "Expensive Book";
    book3.price = new BigDecimal("50.00");
    book3.stock = 3;
    book3.persist();

    List<Item> items = Item.findByPriceRange(new BigDecimal("10.00"), new BigDecimal("20.00"));

    assertEquals(1, items.size());
    assertEquals("Medium Book", items.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldFindOutOfStock() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Book book1 = new Book();
    book1.title = "In Stock Book";
    book1.price = new BigDecimal("10.00");
    book1.stock = 10;
    book1.persist();

    Book book2 = new Book();
    book2.title = "Out of Stock Book";
    book2.price = new BigDecimal("15.00");
    book2.stock = 0;
    book2.persist();

    List<Item> items = Item.findOutOfStock();

    assertEquals(1, items.size());
    assertEquals("Out of Stock Book", items.get(0).title);
    assertEquals(0, items.get(0).stock);
  }

  @Test
  @TestTransaction
  void shouldNotFindOutOfStock() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Book book = new Book();
    book.title = "In Stock Book";
    book.price = new BigDecimal("10.00");
    book.stock = 10;
    book.persist();

    List<Item> items = Item.findOutOfStock();

    assertTrue(items.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindByPriceLessThan() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Book book1 = new Book();
    book1.title = "Cheap Book";
    book1.price = new BigDecimal("5.00");
    book1.stock = 10;
    book1.persist();

    Book book2 = new Book();
    book2.title = "Expensive Book";
    book2.price = new BigDecimal("50.00");
    book2.stock = 3;
    book2.persist();

    List<Item> items = Item.findByPriceLessThan(new BigDecimal("10.00"));

    assertEquals(1, items.size());
    assertEquals("Cheap Book", items.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldFindCreatedAfter() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Instant pastDate = Instant.now().minus(1, ChronoUnit.DAYS);

    Book book = new Book();
    book.title = "New Book";
    book.price = new BigDecimal("10.00");
    book.stock = 10;
    book.persist();

    List<Item> items = Item.findCreatedAfter(pastDate);

    assertEquals(1, items.size());
    assertEquals("New Book", items.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindCreatedAfter() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Instant futureDate = Instant.now().plus(1, ChronoUnit.DAYS);

    Book book = new Book();
    book.title = "Old Book";
    book.price = new BigDecimal("10.00");
    book.stock = 10;
    book.persist();

    List<Item> items = Item.findCreatedAfter(futureDate);

    assertTrue(items.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldCountInStock() {
    Track.deleteAll();
    CD.deleteAll();
    Book.deleteAll();

    Book book1 = new Book();
    book1.title = "In Stock Book 1";
    book1.price = new BigDecimal("10.00");
    book1.stock = 10;
    book1.persist();

    Book book2 = new Book();
    book2.title = "In Stock Book 2";
    book2.price = new BigDecimal("15.00");
    book2.stock = 5;
    book2.persist();

    Book book3 = new Book();
    book3.title = "Out of Stock Book";
    book3.price = new BigDecimal("20.00");
    book3.stock = 0;
    book3.persist();

    long count = Item.countInStock();

    assertEquals(2, count);
  }
}
