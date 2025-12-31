package com.pluralsight.persistence.catalog.model;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

@QuarkusTest
class CatalogMockTest {

  // ======================================
  // Book Tests (Active Record Pattern)
  // ======================================

  @Test
  void shouldMockBookCount() {
    PanacheMock.mock(Book.class);

    Mockito.when(Book.count()).thenReturn(42L);

    assertEquals(42L, Book.count());

    PanacheMock.verify(Book.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockBookFindById() {
    PanacheMock.mock(Book.class);

    Book mockBook = new Book();
    mockBook.id = 1L;
    mockBook.title = "Mocked Book Title";
    mockBook.price = new BigDecimal("29.99");
    mockBook.stock = 100;
    mockBook.isbn = "1234567890123";
    mockBook.language = Language.ENGLISH;

    Mockito.when(Book.findById(1L)).thenReturn(mockBook);

    Book found = Book.findById(1L);

    assertNotNull(found);
    assertEquals("Mocked Book Title", found.title);
    assertEquals(new BigDecimal("29.99"), found.price);
    assertEquals(Language.ENGLISH, found.language);

    PanacheMock.verify(Book.class, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockBookListAll() {
    PanacheMock.mock(Book.class);

    Book book1 = new Book();
    book1.id = 1L;
    book1.title = "Book One";
    book1.price = new BigDecimal("19.99");

    Book book2 = new Book();
    book2.id = 2L;
    book2.title = "Book Two";
    book2.price = new BigDecimal("24.99");

    Mockito.when(Book.listAll()).thenReturn(List.of(book1, book2));

    List<Book> books = Book.listAll();

    assertEquals(2, books.size());
    assertEquals("Book One", books.get(0).title);
    assertEquals("Book Two", books.get(1).title);

    PanacheMock.verify(Book.class, Mockito.times(1)).listAll();
  }

  @Test
  void shouldMockBookFindByLanguage() {
    PanacheMock.mock(Book.class);

    Book englishBook = new Book();
    englishBook.id = 1L;
    englishBook.title = "English Novel";
    englishBook.language = Language.ENGLISH;

    Mockito.when(Book.list("language", Language.ENGLISH)).thenReturn(List.of(englishBook));

    List<Book> englishBooks = Book.list("language", Language.ENGLISH);

    assertEquals(1, englishBooks.size());
    assertEquals("English Novel", englishBooks.get(0).title);
    assertEquals(Language.ENGLISH, englishBooks.get(0).language);

    PanacheMock.verify(Book.class, Mockito.times(1)).list("language", Language.ENGLISH);
  }

  @Test
  void shouldMockBookDeleteById() {
    PanacheMock.mock(Book.class);

    Mockito.when(Book.deleteById(1L)).thenReturn(true);
    Mockito.when(Book.deleteById(999L)).thenReturn(false);

    assertTrue(Book.deleteById(1L));
    assertFalse(Book.deleteById(999L));

    PanacheMock.verify(Book.class, Mockito.times(1)).deleteById(1L);
    PanacheMock.verify(Book.class, Mockito.times(1)).deleteById(999L);
  }

  // ======================================
  // CD Tests (Active Record Pattern)
  // ======================================

  @Test
  void shouldMockCDCount() {
    PanacheMock.mock(CD.class);

    Mockito.when(CD.count()).thenReturn(25L);

    assertEquals(25L, CD.count());

    PanacheMock.verify(CD.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockCDFindById() {
    PanacheMock.mock(CD.class);

    CD mockCD = new CD();
    mockCD.id = 1L;
    mockCD.title = "Mocked Album";
    mockCD.price = new BigDecimal("19.99");
    mockCD.stock = 50;
    mockCD.ean = "1234567890123";
    mockCD.musicCompany = "Mock Records";
    mockCD.genre = MusicGenre.ROCK;

    Mockito.when(CD.findById(1L)).thenReturn(mockCD);

    CD found = CD.findById(1L);

    assertNotNull(found);
    assertEquals("Mocked Album", found.title);
    assertEquals("Mock Records", found.musicCompany);
    assertEquals(MusicGenre.ROCK, found.genre);

    PanacheMock.verify(CD.class, Mockito.times(1)).findById(1L);
  }

  @Test
  void shouldMockCDFindByGenre() {
    PanacheMock.mock(CD.class);

    CD jazzCD = new CD();
    jazzCD.id = 1L;
    jazzCD.title = "Jazz Classics";
    jazzCD.genre = MusicGenre.JAZZ;

    CD jazzCD2 = new CD();
    jazzCD2.id = 2L;
    jazzCD2.title = "More Jazz";
    jazzCD2.genre = MusicGenre.JAZZ;

    Mockito.when(CD.list("genre", MusicGenre.JAZZ)).thenReturn(List.of(jazzCD, jazzCD2));

    List<CD> jazzCDs = CD.list("genre", MusicGenre.JAZZ);

    assertEquals(2, jazzCDs.size());
    assertTrue(jazzCDs.stream().allMatch(cd -> cd.genre == MusicGenre.JAZZ));

    PanacheMock.verify(CD.class, Mockito.times(1)).list("genre", MusicGenre.JAZZ);
  }

  // ======================================
  // Author Tests (Active Record Pattern)
  // ======================================

  @Test
  void shouldMockAuthorCount() {
    PanacheMock.mock(Author.class);

    Mockito.when(Author.count()).thenReturn(15L);

    assertEquals(15L, Author.count());

    PanacheMock.verify(Author.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockAuthorFindById() {
    PanacheMock.mock(Author.class);

    Author mockAuthor = new Author();
    mockAuthor.id = 1L;
    mockAuthor.firstName = "Jane";
    mockAuthor.lastName = "Doe";
    mockAuthor.bio = "Famous author";
    mockAuthor.preferredLanguage = Language.ENGLISH;

    Mockito.when(Author.findById(1L)).thenReturn(mockAuthor);

    Author found = Author.findById(1L);

    assertNotNull(found);
    assertEquals("Jane", found.firstName);
    assertEquals("Doe", found.lastName);
    assertEquals(Language.ENGLISH, found.preferredLanguage);

    PanacheMock.verify(Author.class, Mockito.times(1)).findById(1L);
  }

  // ======================================
  // Musician Tests (Active Record Pattern)
  // ======================================

  @Test
  void shouldMockMusicianCount() {
    PanacheMock.mock(Musician.class);

    Mockito.when(Musician.count()).thenReturn(30L);

    assertEquals(30L, Musician.count());

    PanacheMock.verify(Musician.class, Mockito.times(1)).count();
  }

  @Test
  void shouldMockMusicianFindById() {
    PanacheMock.mock(Musician.class);

    Musician mockMusician = new Musician();
    mockMusician.id = 1L;
    mockMusician.firstName = "John";
    mockMusician.lastName = "Smith";
    mockMusician.stageName = "Johnny Rock";
    mockMusician.instrument = "Guitar";

    Mockito.when(Musician.findById(1L)).thenReturn(mockMusician);

    Musician found = Musician.findById(1L);

    assertNotNull(found);
    assertEquals("John", found.firstName);
    assertEquals("Johnny Rock", found.stageName);
    assertEquals("Guitar", found.instrument);

    PanacheMock.verify(Musician.class, Mockito.times(1)).findById(1L);
  }

  // ======================================
  // Combined Active Record Tests
  // ======================================

  @Test
  void shouldMockMultipleEntitiesSimultaneously() {
    PanacheMock.mock(Book.class);
    PanacheMock.mock(CD.class);

    Mockito.when(Book.count()).thenReturn(100L);
    Mockito.when(CD.count()).thenReturn(50L);

    assertEquals(100L, Book.count());
    assertEquals(50L, CD.count());
    assertEquals(150L, Book.count() + CD.count());

    PanacheMock.verify(Book.class, Mockito.times(2)).count();
    PanacheMock.verify(CD.class, Mockito.times(2)).count();
  }
}
