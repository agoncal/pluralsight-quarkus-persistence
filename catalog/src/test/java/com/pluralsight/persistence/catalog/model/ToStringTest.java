package com.pluralsight.persistence.catalog.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

class ToStringTest {

  @Test
  void shouldDisplayBook() {
    Book book = new Book();
    book.id = 1L;
    book.title = "Effective Java";
    book.price = new BigDecimal("45.99");
    book.stock = 10;
    book.isbn = "9780134685991";
    book.language = Language.ENGLISH;

    String result = book.toString();

    assertTrue(result.startsWith("Book{"));
    assertTrue(result.contains("id=1"));
    assertTrue(result.contains("title='Effective Java'"));
    assertTrue(result.contains("price=45.99"));
    assertTrue(result.contains("stock=10"));
    assertTrue(result.contains("isbn='9780134685991'"));
    assertTrue(result.contains("language=ENGLISH"));
  }

  @Test
  void shouldDisplayCD() {
    CD cd = new CD();
    cd.id = 2L;
    cd.title = "Abbey Road";
    cd.price = new BigDecimal("19.99");
    cd.stock = 50;
    cd.ean = "1234567890123";
    cd.genre = MusicGenre.ROCK;

    String result = cd.toString();

    assertTrue(result.startsWith("CD{"));
    assertTrue(result.contains("id=2"));
    assertTrue(result.contains("title='Abbey Road'"));
    assertTrue(result.contains("price=19.99"));
    assertTrue(result.contains("stock=50"));
    assertTrue(result.contains("ean='1234567890123'"));
    assertTrue(result.contains("genre=ROCK"));
  }

  @Test
  void shouldDisplayAuthor() {
    Author author = new Author();
    author.id = 3L;
    author.firstName = "Joshua";
    author.lastName = "Bloch";
    author.preferredLanguage = Language.ENGLISH;

    String result = author.toString();

    assertTrue(result.startsWith("Author{"));
    assertTrue(result.contains("id=3"));
    assertTrue(result.contains("firstName='Joshua'"));
    assertTrue(result.contains("lastName='Bloch'"));
    assertTrue(result.contains("preferredLanguage=ENGLISH"));
  }

  @Test
  void shouldDisplayMusician() {
    Musician musician = new Musician();
    musician.id = 4L;
    musician.firstName = "John";
    musician.lastName = "Lennon";
    musician.stageName = "John Lennon";
    musician.instrument = "Guitar";

    String result = musician.toString();

    assertTrue(result.startsWith("Musician{"));
    assertTrue(result.contains("id=4"));
    assertTrue(result.contains("firstName='John'"));
    assertTrue(result.contains("lastName='Lennon'"));
    assertTrue(result.contains("stageName='John Lennon'"));
    assertTrue(result.contains("instrument='Guitar'"));
  }

  @Test
  void shouldDisplayPublisher() {
    Publisher publisher = new Publisher();
    publisher.id = 5L;
    publisher.name = "Addison-Wesley";
    publisher.country = "USA";

    String result = publisher.toString();

    assertTrue(result.startsWith("Publisher{"));
    assertTrue(result.contains("id=5"));
    assertTrue(result.contains("name='Addison-Wesley'"));
    assertTrue(result.contains("country='USA'"));
  }

  @Test
  void shouldDisplayTrack() {
    Track track = new Track();
    track.id = 6L;
    track.title = "Come Together";
    track.trackNumber = 1;
    track.duration = Duration.ofMinutes(4).plusSeconds(20);

    String result = track.toString();

    assertTrue(result.startsWith("Track{"));
    assertTrue(result.contains("id=6"));
    assertTrue(result.contains("title='Come Together'"));
    assertTrue(result.contains("trackNumber=1"));
    assertTrue(result.contains("duration="));
  }

  @Test
  void shouldDisplayBookWithNullFields() {
    Book book = new Book();

    String result = book.toString();

    assertTrue(result.startsWith("Book{"));
    assertTrue(result.contains("id=null"));
    assertTrue(result.contains("title='null'"));
  }

  @Test
  void shouldDisplayAuthorWithNullFields() {
    Author author = new Author();

    String result = author.toString();

    assertTrue(result.startsWith("Author{"));
    assertTrue(result.contains("id=null"));
  }
}
