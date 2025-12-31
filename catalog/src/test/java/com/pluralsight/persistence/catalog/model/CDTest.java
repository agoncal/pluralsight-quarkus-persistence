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
import java.util.List;

@QuarkusTest
class CDTest {

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
  void shouldNotFindCDById() {
    CD found = CD.findById(999999L);
    assertNull(found);
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

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingCD() {
    boolean deleted = CD.deleteById(999999L);
    assertFalse(deleted);
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
  void shouldListAllCDs() {
    long initialCount = CD.count();

    CD cd1 = new CD();
    cd1.title = "Album One";
    cd1.price = new BigDecimal("10.00");
    cd1.stock = 10;
    cd1.ean = "1111111111111";
    cd1.musicCompany = "Label One";
    cd1.genre = MusicGenre.ROCK;
    cd1.persist();

    CD cd2 = new CD();
    cd2.title = "Album Two";
    cd2.price = new BigDecimal("20.00");
    cd2.stock = 20;
    cd2.ean = "2222222222222";
    cd2.musicCompany = "Label Two";
    cd2.genre = MusicGenre.JAZZ;
    cd2.persist();

    List<CD> cds = CD.listAll();

    assertEquals(initialCount + 2, cds.size());
  }

  @Test
  @TestTransaction
  void shouldDeleteAllCDs() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd1 = new CD();
    cd1.title = "Album One";
    cd1.price = new BigDecimal("10.00");
    cd1.stock = 10;
    cd1.ean = "1111111111111";
    cd1.musicCompany = "Label One";
    cd1.genre = MusicGenre.ROCK;
    cd1.persist();

    CD cd2 = new CD();
    cd2.title = "Album Two";
    cd2.price = new BigDecimal("20.00");
    cd2.stock = 20;
    cd2.ean = "2222222222222";
    cd2.musicCompany = "Label Two";
    cd2.genre = MusicGenre.JAZZ;
    cd2.persist();

    assertEquals(2, CD.count());

    CD.deleteAll();

    assertEquals(0, CD.count());
  }

  @Test
  @TestTransaction
  void shouldFindCDByGenre() {
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
  void shouldNotFindCDByGenre() {
    Track.deleteAll();
    CD.deleteAll();

    List<CD> cds = CD.list("genre", MusicGenre.RAP);

    assertTrue(cds.isEmpty());
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
}
