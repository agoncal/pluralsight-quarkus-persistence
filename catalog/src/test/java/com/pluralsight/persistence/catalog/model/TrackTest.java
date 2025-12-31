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
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@QuarkusTest
class TrackTest {

  private CD createCD(String title) {
    CD cd = new CD();
    cd.title = title;
    cd.price = new BigDecimal("15.00");
    cd.stock = 10;
    cd.ean = String.valueOf(System.nanoTime()).substring(0, 13);
    cd.musicCompany = "Test Records";
    cd.genre = MusicGenre.ROCK;
    cd.persist();
    return cd;
  }

  @Test
  @TestTransaction
  void shouldCreateTrack() {
    CD cd = createCD("Test Album");

    Track track = new Track();
    track.title = "Test Song";
    track.duration = Duration.ofMinutes(3).plusSeconds(30);
    track.trackNumber = 1;
    track.cd = cd;

    track.persist();

    assertNotNull(track.id);
  }

  @Test
  @TestTransaction
  void shouldFindTrackById() {
    CD cd = createCD("Findable Album");

    Track track = new Track();
    track.title = "Findable Song";
    track.duration = Duration.ofMinutes(4);
    track.trackNumber = 1;
    track.cd = cd;
    track.persist();

    Track found = Track.findById(track.id);

    assertNotNull(found);
    assertEquals("Findable Song", found.title);
    assertEquals(Duration.ofMinutes(4), found.duration);
  }

  @Test
  @TestTransaction
  void shouldNotFindTrackById() {
    Track found = Track.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeleteTrack() {
    CD cd = createCD("Album for Deletion");

    Track track = new Track();
    track.title = "To Be Deleted";
    track.duration = Duration.ofMinutes(2);
    track.trackNumber = 1;
    track.cd = cd;
    track.persist();

    Long trackId = track.id;
    assertNotNull(Track.findById(trackId));

    boolean deleted = Track.deleteById(trackId);

    assertTrue(deleted);
    assertNull(Track.findById(trackId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingTrack() {
    boolean deleted = Track.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountTracks() {
    long initialCount = Track.count();

    CD cd = createCD("Count Test Album");

    Track track = new Track();
    track.title = "Count Test Song";
    track.duration = Duration.ofMinutes(3);
    track.trackNumber = 1;
    track.cd = cd;
    track.persist();

    assertEquals(initialCount + 1, Track.count());
  }

  @Test
  @TestTransaction
  void shouldListAllTracks() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("List Test Album");

    Track track1 = new Track();
    track1.title = "Track One";
    track1.duration = Duration.ofMinutes(3);
    track1.trackNumber = 1;
    track1.cd = cd;
    track1.persist();

    Track track2 = new Track();
    track2.title = "Track Two";
    track2.duration = Duration.ofMinutes(4);
    track2.trackNumber = 2;
    track2.cd = cd;
    track2.persist();

    List<Track> tracks = Track.listAll();

    assertEquals(2, tracks.size());
  }

  @Test
  @TestTransaction
  void shouldDeleteAllTracks() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Delete All Test Album");

    Track track1 = new Track();
    track1.title = "Track One";
    track1.duration = Duration.ofMinutes(3);
    track1.trackNumber = 1;
    track1.cd = cd;
    track1.persist();

    Track track2 = new Track();
    track2.title = "Track Two";
    track2.duration = Duration.ofMinutes(4);
    track2.trackNumber = 2;
    track2.cd = cd;
    track2.persist();

    assertEquals(2, Track.count());

    Track.deleteAll();

    assertEquals(0, Track.count());
  }

  @Test
  @TestTransaction
  void shouldFindTrackByCd() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd1 = createCD("Album One");
    CD cd2 = createCD("Album Two");

    Track track1 = new Track();
    track1.title = "Track from Album One";
    track1.duration = Duration.ofMinutes(3);
    track1.trackNumber = 1;
    track1.cd = cd1;
    track1.persist();

    Track track2 = new Track();
    track2.title = "Track from Album Two";
    track2.duration = Duration.ofMinutes(4);
    track2.trackNumber = 1;
    track2.cd = cd2;
    track2.persist();

    List<Track> tracks = Track.findByCd(cd1);

    assertEquals(1, tracks.size());
    assertEquals("Track from Album One", tracks.get(0).title);
  }

  @Test
  @TestTransaction
  void shouldNotFindTrackByCd() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Empty Album");

    List<Track> tracks = Track.findByCd(cd);

    assertTrue(tracks.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindTrackByCdId() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Album for ID Search");

    Track track1 = new Track();
    track1.title = "First Song";
    track1.duration = Duration.ofMinutes(3);
    track1.trackNumber = 1;
    track1.cd = cd;
    track1.persist();

    Track track2 = new Track();
    track2.title = "Second Song";
    track2.duration = Duration.ofMinutes(4);
    track2.trackNumber = 2;
    track2.cd = cd;
    track2.persist();

    List<Track> tracks = Track.findByCdId(cd.id);

    assertEquals(2, tracks.size());
  }

  @Test
  @TestTransaction
  void shouldFindTrackByTitleContaining() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Mixed Album");

    Track track1 = new Track();
    track1.title = "Love Song";
    track1.duration = Duration.ofMinutes(3);
    track1.trackNumber = 1;
    track1.cd = cd;
    track1.persist();

    Track track2 = new Track();
    track2.title = "Another Love";
    track2.duration = Duration.ofMinutes(4);
    track2.trackNumber = 2;
    track2.cd = cd;
    track2.persist();

    Track track3 = new Track();
    track3.title = "Rock Anthem";
    track3.duration = Duration.ofMinutes(5);
    track3.trackNumber = 3;
    track3.cd = cd;
    track3.persist();

    List<Track> tracks = Track.findByTitleContaining("love");

    assertEquals(2, tracks.size());
    assertTrue(tracks.stream().allMatch(t -> t.title.toLowerCase().contains("love")));
  }

  @Test
  @TestTransaction
  void shouldNotFindTrackByTitleContaining() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("No Match Album");

    Track track = new Track();
    track.title = "Some Song";
    track.duration = Duration.ofMinutes(3);
    track.trackNumber = 1;
    track.cd = cd;
    track.persist();

    List<Track> tracks = Track.findByTitleContaining("nonexistent");

    assertTrue(tracks.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindLongestTrack() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Duration Test Album");

    Track shortTrack = new Track();
    shortTrack.title = "Short Track";
    shortTrack.duration = Duration.ofMinutes(2);
    shortTrack.trackNumber = 1;
    shortTrack.cd = cd;
    shortTrack.persist();

    Track longTrack = new Track();
    longTrack.title = "Long Track";
    longTrack.duration = Duration.ofMinutes(10);
    longTrack.trackNumber = 2;
    longTrack.cd = cd;
    longTrack.persist();

    Track mediumTrack = new Track();
    mediumTrack.title = "Medium Track";
    mediumTrack.duration = Duration.ofMinutes(5);
    mediumTrack.trackNumber = 3;
    mediumTrack.cd = cd;
    mediumTrack.persist();

    Optional<Track> longest = Track.findLongestTrack();

    assertTrue(longest.isPresent());
    assertEquals("Long Track", longest.get().title);
    assertEquals(Duration.ofMinutes(10), longest.get().duration);
  }

  @Test
  @TestTransaction
  void shouldFindShortestTrack() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Duration Test Album 2");

    Track shortTrack = new Track();
    shortTrack.title = "Shortest Track";
    shortTrack.duration = Duration.ofMinutes(1).plusSeconds(30);
    shortTrack.trackNumber = 1;
    shortTrack.cd = cd;
    shortTrack.persist();

    Track longTrack = new Track();
    longTrack.title = "Long Track";
    longTrack.duration = Duration.ofMinutes(8);
    longTrack.trackNumber = 2;
    longTrack.cd = cd;
    longTrack.persist();

    Optional<Track> shortest = Track.findShortestTrack();

    assertTrue(shortest.isPresent());
    assertEquals("Shortest Track", shortest.get().title);
    assertEquals(Duration.ofMinutes(1).plusSeconds(30), shortest.get().duration);
  }

  @Test
  @TestTransaction
  void shouldNotFindLongestTrackWhenEmpty() {
    Track.deleteAll();

    Optional<Track> longest = Track.findLongestTrack();

    assertTrue(longest.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldCountByCd() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd1 = createCD("Album with Many Tracks");
    CD cd2 = createCD("Album with Few Tracks");

    for (int i = 1; i <= 5; i++) {
      Track track = new Track();
      track.title = "Track " + i;
      track.duration = Duration.ofMinutes(3);
      track.trackNumber = i;
      track.cd = cd1;
      track.persist();
    }

    Track track = new Track();
    track.title = "Single Track";
    track.duration = Duration.ofMinutes(4);
    track.trackNumber = 1;
    track.cd = cd2;
    track.persist();

    assertEquals(5, Track.countByCd(cd1));
    assertEquals(1, Track.countByCd(cd2));
  }

  @Test
  @TestTransaction
  void shouldSumDurationByCd() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Duration Sum Album");

    Track track1 = new Track();
    track1.title = "Track 1";
    track1.duration = Duration.ofMinutes(3);
    track1.trackNumber = 1;
    track1.cd = cd;
    track1.persist();

    Track track2 = new Track();
    track2.title = "Track 2";
    track2.duration = Duration.ofMinutes(4).plusSeconds(30);
    track2.trackNumber = 2;
    track2.cd = cd;
    track2.persist();

    Track track3 = new Track();
    track3.title = "Track 3";
    track3.duration = Duration.ofMinutes(2).plusSeconds(30);
    track3.trackNumber = 3;
    track3.cd = cd;
    track3.persist();

    Duration totalDuration = Track.sumDurationByCd(cd);

    // 3:00 + 4:30 + 2:30 = 10:00
    assertEquals(Duration.ofMinutes(10), totalDuration);
  }

  @Test
  @TestTransaction
  void shouldSumZeroDurationForEmptyCd() {
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("Empty Duration Album");

    Duration totalDuration = Track.sumDurationByCd(cd);

    assertEquals(Duration.ZERO, totalDuration);
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnTrack() {
    // Clear all tracks and CDs
    Track.deleteAll();
    CD.deleteAll();

    CD cd = createCD("CRUD Test Album");

    // Find all and check there is zero
    assertEquals(0, Track.count());
    assertTrue(Track.listAll().isEmpty());

    // Create two items
    Track track1 = new Track();
    track1.title = "First Track";
    track1.duration = Duration.ofMinutes(3);
    track1.trackNumber = 1;
    track1.cd = cd;
    track1.persist();

    Track track2 = new Track();
    track2.title = "Second Track";
    track2.duration = Duration.ofMinutes(4);
    track2.trackNumber = 2;
    track2.cd = cd;
    track2.persist();

    // Find all and check there is two
    assertEquals(2, Track.count());
    assertEquals(2, Track.listAll().size());

    // Find by id and check it's the right one
    Track foundTrack1 = Track.findById(track1.id);
    assertNotNull(foundTrack1);
    assertEquals("First Track", foundTrack1.title);
    assertEquals(Duration.ofMinutes(3), foundTrack1.duration);

    // Update one item, find by id and check it's ok
    foundTrack1.title = "Updated First Track";
    foundTrack1.duration = Duration.ofMinutes(5);
    Track updatedTrack = Track.findById(track1.id);
    assertEquals("Updated First Track", updatedTrack.title);
    assertEquals(Duration.ofMinutes(5), updatedTrack.duration);

    // Delete one item and check there is only one now
    Track.deleteById(track1.id);
    assertEquals(1, Track.count());
    assertNull(Track.findById(track1.id));
    assertNotNull(Track.findById(track2.id));

    // Delete the second item and check findall returns zero
    Track.deleteById(track2.id);
    assertEquals(0, Track.count());
    assertTrue(Track.listAll().isEmpty());
  }
}
