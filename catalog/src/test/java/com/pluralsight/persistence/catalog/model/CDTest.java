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
import java.time.LocalDate;
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

    List<CD> classicalCDs = CD.findByGenre(MusicGenre.CLASSICAL);

    assertTrue(classicalCDs.stream().anyMatch(c -> c.title.equals("Symphony No. 5")));
    assertTrue(classicalCDs.stream().allMatch(c -> c.genre == MusicGenre.CLASSICAL));
  }

  @Test
  @TestTransaction
  void shouldNotFindCDByGenre() {
    Track.deleteAll();
    CD.deleteAll();

    List<CD> cds = CD.findByGenre(MusicGenre.RAP);

    assertTrue(cds.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindCDByEan() {
    CD cd = new CD();
    cd.title = "EAN Test Album";
    cd.price = new BigDecimal("15.00");
    cd.stock = 20;
    cd.ean = "5555555555555";
    cd.musicCompany = "Test Label";
    cd.genre = MusicGenre.ROCK;
    cd.persist();

    CD found = CD.findByEan("5555555555555");

    assertNotNull(found);
    assertEquals("EAN Test Album", found.title);
  }

  @Test
  @TestTransaction
  void shouldNotFindCDByEan() {
    CD found = CD.findByEan("0000000000000");

    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldFindCDByMusicCompany() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd1 = new CD();
    cd1.title = "Album from Sony";
    cd1.price = new BigDecimal("15.00");
    cd1.stock = 20;
    cd1.ean = "3333333333331";
    cd1.musicCompany = "Sony Music";
    cd1.genre = MusicGenre.POP;
    cd1.persist();

    CD cd2 = new CD();
    cd2.title = "Album from Warner";
    cd2.price = new BigDecimal("18.00");
    cd2.stock = 15;
    cd2.ean = "3333333333332";
    cd2.musicCompany = "Warner Music";
    cd2.genre = MusicGenre.ROCK;
    cd2.persist();

    List<CD> cds = CD.findByMusicCompany("Sony Music");

    assertEquals(1, cds.size());
    assertEquals("Album from Sony", cds.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindCDByMusicCompany() {
    Track.deleteAll();
    CD.deleteAll();

    List<CD> cds = CD.findByMusicCompany("Unknown Label");

    assertTrue(cds.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindCDByMusician() {
    Musician musician = new Musician();
    musician.firstName = "John";
    musician.lastName = "Rocker";
    musician.instrument = "Guitar";
    musician.persist();

    CD cd = new CD();
    cd.title = "Musician's Album";
    cd.price = new BigDecimal("20.00");
    cd.stock = 25;
    cd.ean = "4444444444444";
    cd.musicCompany = "Rock Label";
    cd.genre = MusicGenre.ROCK;
    cd.musicians.add(musician);
    cd.persist();

    List<CD> cds = CD.findByMusician(musician);

    assertEquals(1, cds.size());
    assertEquals("Musician's Album", cds.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindCDByMusician() {
    Musician musician = new Musician();
    musician.firstName = "Unknown";
    musician.lastName = "Artist";
    musician.persist();

    List<CD> cds = CD.findByMusician(musician);

    assertTrue(cds.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindCDReleasedBefore() {
    Track.deleteAll();
    CD.deleteAll();

    CD oldCD = new CD();
    oldCD.title = "Old Album";
    oldCD.price = new BigDecimal("10.00");
    oldCD.stock = 5;
    oldCD.ean = "8888888888881";
    oldCD.musicCompany = "Vintage Records";
    oldCD.genre = MusicGenre.CLASSICAL;
    oldCD.releaseDate = LocalDate.of(1990, 5, 15);
    oldCD.persist();

    CD newCD = new CD();
    newCD.title = "New Album";
    newCD.price = new BigDecimal("20.00");
    newCD.stock = 30;
    newCD.ean = "8888888888882";
    newCD.musicCompany = "Modern Records";
    newCD.genre = MusicGenre.POP;
    newCD.releaseDate = LocalDate.of(2023, 3, 20);
    newCD.persist();

    List<CD> cds = CD.findReleasedBefore(LocalDate.of(2000, 1, 1));

    assertEquals(1, cds.size());
    assertEquals("Old Album", cds.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindCDReleasedBefore() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = new CD();
    cd.title = "Modern Album";
    cd.price = new BigDecimal("18.00");
    cd.stock = 20;
    cd.ean = "9999999999991";
    cd.musicCompany = "New Label";
    cd.genre = MusicGenre.ELECTRONIC;
    cd.releaseDate = LocalDate.of(2020, 6, 1);
    cd.persist();

    List<CD> cds = CD.findReleasedBefore(LocalDate.of(2015, 1, 1));

    assertTrue(cds.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindCDReleasedAfter() {
    Track.deleteAll();
    CD.deleteAll();

    CD oldCD = new CD();
    oldCD.title = "Classic Album";
    oldCD.price = new BigDecimal("12.00");
    oldCD.stock = 10;
    oldCD.ean = "1212121212121";
    oldCD.musicCompany = "Old Label";
    oldCD.genre = MusicGenre.JAZZ;
    oldCD.releaseDate = LocalDate.of(1985, 8, 10);
    oldCD.persist();

    CD newCD = new CD();
    newCD.title = "Fresh Album";
    newCD.price = new BigDecimal("22.00");
    newCD.stock = 40;
    newCD.ean = "1212121212122";
    newCD.musicCompany = "Fresh Records";
    newCD.genre = MusicGenre.ROCK;
    newCD.releaseDate = LocalDate.of(2022, 11, 5);
    newCD.persist();

    List<CD> cds = CD.findReleasedAfter(LocalDate.of(2020, 1, 1));

    assertEquals(1, cds.size());
    assertEquals("Fresh Album", cds.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldFindCDReleasedInYear() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd2019 = new CD();
    cd2019.title = "Album 2019";
    cd2019.price = new BigDecimal("15.00");
    cd2019.stock = 15;
    cd2019.ean = "1313131313131";
    cd2019.musicCompany = "Label 2019";
    cd2019.genre = MusicGenre.POP;
    cd2019.releaseDate = LocalDate.of(2019, 4, 20);
    cd2019.persist();

    CD cd2020a = new CD();
    cd2020a.title = "Album 2020 First";
    cd2020a.price = new BigDecimal("18.00");
    cd2020a.stock = 20;
    cd2020a.ean = "1313131313132";
    cd2020a.musicCompany = "Label 2020";
    cd2020a.genre = MusicGenre.ROCK;
    cd2020a.releaseDate = LocalDate.of(2020, 2, 10);
    cd2020a.persist();

    CD cd2020b = new CD();
    cd2020b.title = "Album 2020 Second";
    cd2020b.price = new BigDecimal("20.00");
    cd2020b.stock = 25;
    cd2020b.ean = "1313131313133";
    cd2020b.musicCompany = "Label 2020";
    cd2020b.genre = MusicGenre.JAZZ;
    cd2020b.releaseDate = LocalDate.of(2020, 9, 15);
    cd2020b.persist();

    CD cd2021 = new CD();
    cd2021.title = "Album 2021";
    cd2021.price = new BigDecimal("22.00");
    cd2021.stock = 30;
    cd2021.ean = "1313131313134";
    cd2021.musicCompany = "Label 2021";
    cd2021.genre = MusicGenre.ELECTRONIC;
    cd2021.releaseDate = LocalDate.of(2021, 7, 1);
    cd2021.persist();

    List<CD> cds = CD.findReleasedInYear(2020);

    assertEquals(2, cds.size());
    assertTrue(cds.stream().allMatch(c -> c.releaseDate.getYear() == 2020));
  }

  @Test
  @TestTransaction
  void shouldNotFindCDReleasedInYear() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = new CD();
    cd.title = "Album 2023";
    cd.price = new BigDecimal("19.00");
    cd.stock = 35;
    cd.ean = "1414141414141";
    cd.musicCompany = "New Label";
    cd.genre = MusicGenre.POP;
    cd.releaseDate = LocalDate.of(2023, 5, 1);
    cd.persist();

    List<CD> cds = CD.findReleasedInYear(2010);

    assertTrue(cds.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldCountByGenre() {
    Track.deleteAll();
    CD.deleteAll();

    CD rock1 = new CD();
    rock1.title = "Rock Album 1";
    rock1.price = new BigDecimal("15.00");
    rock1.stock = 20;
    rock1.ean = "1515151515151";
    rock1.musicCompany = "Rock Label";
    rock1.genre = MusicGenre.ROCK;
    rock1.persist();

    CD rock2 = new CD();
    rock2.title = "Rock Album 2";
    rock2.price = new BigDecimal("18.00");
    rock2.stock = 25;
    rock2.ean = "1515151515152";
    rock2.musicCompany = "Rock Label";
    rock2.genre = MusicGenre.ROCK;
    rock2.persist();

    CD jazz = new CD();
    jazz.title = "Jazz Album";
    jazz.price = new BigDecimal("20.00");
    jazz.stock = 15;
    jazz.ean = "1515151515153";
    jazz.musicCompany = "Jazz Label";
    jazz.genre = MusicGenre.JAZZ;
    jazz.persist();

    long rockCount = CD.countByGenre(MusicGenre.ROCK);
    long jazzCount = CD.countByGenre(MusicGenre.JAZZ);
    long popCount = CD.countByGenre(MusicGenre.POP);

    assertEquals(2, rockCount);
    assertEquals(1, jazzCount);
    assertEquals(0, popCount);
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
