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
class MusicianTest {

  @Test
  @TestTransaction
  void shouldCreateMusician() {
    Musician musician = new Musician();
    musician.firstName = "John";
    musician.lastName = "Lennon";
    musician.stageName = "Johnny";
    musician.instrument = "Guitar";

    musician.persist();

    assertNotNull(musician.id);
  }

  @Test
  @TestTransaction
  void shouldFindMusicianById() {
    Musician musician = new Musician();
    musician.firstName = "Paul";
    musician.lastName = "McCartney";
    musician.stageName = "Macca";
    musician.instrument = "Bass";
    musician.persist();

    Musician found = Musician.findById(musician.id);

    assertNotNull(found);
    assertEquals("Paul", found.firstName);
    assertEquals("Macca", found.stageName);
    assertEquals("Bass", found.instrument);
  }

  @Test
  @TestTransaction
  void shouldNotFindMusicianById() {
    Musician found = Musician.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeleteMusician() {
    Musician musician = new Musician();
    musician.firstName = "Temp";
    musician.lastName = "Musician";
    musician.stageName = "Temp";
    musician.instrument = "Drums";
    musician.persist();

    Long musicianId = musician.id;
    assertNotNull(Musician.findById(musicianId));

    boolean deleted = Musician.deleteById(musicianId);

    assertTrue(deleted);
    assertNull(Musician.findById(musicianId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingMusician() {
    boolean deleted = Musician.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountMusicians() {
    long initialCount = Musician.count();

    Musician musician = new Musician();
    musician.firstName = "Count";
    musician.lastName = "Test";
    musician.stageName = "Counter";
    musician.instrument = "Piano";
    musician.persist();

    assertEquals(initialCount + 1, Musician.count());
  }

  @Test
  @TestTransaction
  void shouldListAllMusicians() {
    long initialCount = Musician.count();

    Musician musician1 = new Musician();
    musician1.firstName = "Musician";
    musician1.lastName = "One";
    musician1.stageName = "M1";
    musician1.instrument = "Guitar";
    musician1.persist();

    Musician musician2 = new Musician();
    musician2.firstName = "Musician";
    musician2.lastName = "Two";
    musician2.stageName = "M2";
    musician2.instrument = "Bass";
    musician2.persist();

    List<Musician> musicians = Musician.listAll();

    assertEquals(initialCount + 2, musicians.size());
  }

  @Test
  @TestTransaction
  void shouldFindMusicianByInstrument() {
    Musician drummer = new Musician();
    drummer.firstName = "Ringo";
    drummer.lastName = "Starr";
    drummer.stageName = "Ringo";
    drummer.instrument = "Drums";
    drummer.persist();

    List<Musician> drummers = Musician.list("instrument", "Drums");

    assertTrue(drummers.stream().anyMatch(m -> m.lastName.equals("Starr")));
    assertTrue(drummers.stream().allMatch(m -> m.instrument.equals("Drums")));
  }

  @Test
  @TestTransaction
  void shouldNotFindMusicianByInstrument() {
    Musician.deleteAll();

    List<Musician> musicians = Musician.list("instrument", "Theremin");

    assertTrue(musicians.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnMusician() {
    // Clear all musicians
    Musician.deleteAll();

    // Find all and check there is zero
    assertEquals(0, Musician.count());
    assertTrue(Musician.listAll().isEmpty());

    // Create two items
    Musician musician1 = new Musician();
    musician1.firstName = "John";
    musician1.lastName = "Lennon";
    musician1.stageName = "Johnny";
    musician1.instrument = "Guitar";
    musician1.persist();

    Musician musician2 = new Musician();
    musician2.firstName = "Paul";
    musician2.lastName = "McCartney";
    musician2.stageName = "Macca";
    musician2.instrument = "Bass";
    musician2.persist();

    // Find all and check there is two
    assertEquals(2, Musician.count());
    assertEquals(2, Musician.listAll().size());

    // Find by id and check it's the right one
    Musician foundMusician1 = Musician.findById(musician1.id);
    assertNotNull(foundMusician1);
    assertEquals("John", foundMusician1.firstName);
    assertEquals("Johnny", foundMusician1.stageName);
    assertEquals("Guitar", foundMusician1.instrument);

    // Update one item, find by id and check it's ok
    foundMusician1.stageName = "John Lennon";
    foundMusician1.instrument = "Piano";
    Musician updatedMusician = Musician.findById(musician1.id);
    assertEquals("John Lennon", updatedMusician.stageName);
    assertEquals("Piano", updatedMusician.instrument);

    // Delete one item and check there is only one now
    Musician.deleteById(musician1.id);
    assertEquals(1, Musician.count());
    assertNull(Musician.findById(musician1.id));
    assertNotNull(Musician.findById(musician2.id));

    // Delete the second item and check findall returns zero
    Musician.deleteById(musician2.id);
    assertEquals(0, Musician.count());
    assertTrue(Musician.listAll().isEmpty());
  }
}
