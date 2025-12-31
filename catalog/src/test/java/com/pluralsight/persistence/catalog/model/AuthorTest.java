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
class AuthorTest {

  @Test
  @TestTransaction
  void shouldCreateAuthor() {
    Author author = new Author();
    author.firstName = "Jane";
    author.lastName = "Doe";
    author.bio = "Famous author";
    author.preferredLanguage = Language.ENGLISH;

    author.persist();

    assertNotNull(author.id);
  }

  @Test
  @TestTransaction
  void shouldFindAuthorById() {
    Author author = new Author();
    author.firstName = "John";
    author.lastName = "Smith";
    author.bio = "Prolific writer";
    author.preferredLanguage = Language.FRENCH;
    author.persist();

    Author found = Author.findById(author.id);

    assertNotNull(found);
    assertEquals("John", found.firstName);
    assertEquals("Smith", found.lastName);
    assertEquals(Language.FRENCH, found.preferredLanguage);
  }

  @Test
  @TestTransaction
  void shouldNotFindAuthorById() {
    Author found = Author.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeleteAuthor() {
    Author author = new Author();
    author.firstName = "To Be";
    author.lastName = "Deleted";
    author.bio = "Temporary author";
    author.preferredLanguage = Language.SPANISH;
    author.persist();

    Long authorId = author.id;
    assertNotNull(Author.findById(authorId));

    boolean deleted = Author.deleteById(authorId);

    assertTrue(deleted);
    assertNull(Author.findById(authorId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingAuthor() {
    boolean deleted = Author.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountAuthors() {
    long initialCount = Author.count();

    Author author = new Author();
    author.firstName = "Count";
    author.lastName = "Test";
    author.bio = "Author for counting";
    author.preferredLanguage = Language.ENGLISH;
    author.persist();

    assertEquals(initialCount + 1, Author.count());
  }

  @Test
  @TestTransaction
  void shouldListAllAuthors() {
    long initialCount = Author.count();

    Author author1 = new Author();
    author1.firstName = "Author";
    author1.lastName = "One";
    author1.bio = "First author";
    author1.preferredLanguage = Language.ENGLISH;
    author1.persist();

    Author author2 = new Author();
    author2.firstName = "Author";
    author2.lastName = "Two";
    author2.bio = "Second author";
    author2.preferredLanguage = Language.FRENCH;
    author2.persist();

    List<Author> authors = Author.listAll();

    assertEquals(initialCount + 2, authors.size());
  }

  @Test
  @TestTransaction
  void shouldDeleteAllAuthors() {
    Author.deleteAll();

    Author author1 = new Author();
    author1.firstName = "Author";
    author1.lastName = "One";
    author1.bio = "First author";
    author1.preferredLanguage = Language.ENGLISH;
    author1.persist();

    Author author2 = new Author();
    author2.firstName = "Author";
    author2.lastName = "Two";
    author2.bio = "Second author";
    author2.preferredLanguage = Language.FRENCH;
    author2.persist();

    assertEquals(2, Author.count());

    Author.deleteAll();

    assertEquals(0, Author.count());
  }

  @Test
  @TestTransaction
  void shouldFindAuthorByPreferredLanguage() {
    Author germanAuthor = new Author();
    germanAuthor.firstName = "German";
    germanAuthor.lastName = "Writer";
    germanAuthor.bio = "Writes in German";
    germanAuthor.preferredLanguage = Language.GERMAN;
    germanAuthor.persist();

    List<Author> germanAuthors = Author.list("preferredLanguage", Language.GERMAN);

    assertTrue(germanAuthors.stream().anyMatch(a -> a.lastName.equals("Writer")));
    assertTrue(germanAuthors.stream().allMatch(a -> a.preferredLanguage == Language.GERMAN));
  }

  @Test
  @TestTransaction
  void shouldNotFindAuthorByPreferredLanguage() {
    Author.deleteAll();

    List<Author> authors = Author.list("preferredLanguage", Language.PORTUGUESE);

    assertTrue(authors.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnAuthor() {
    // Clear all authors
    Author.deleteAll();

    // Find all and check there is zero
    assertEquals(0, Author.count());
    assertTrue(Author.listAll().isEmpty());

    // Create two items
    Author author1 = new Author();
    author1.firstName = "Jane";
    author1.lastName = "Austen";
    author1.bio = "English novelist";
    author1.preferredLanguage = Language.ENGLISH;
    author1.persist();

    Author author2 = new Author();
    author2.firstName = "Victor";
    author2.lastName = "Hugo";
    author2.bio = "French poet and novelist";
    author2.preferredLanguage = Language.FRENCH;
    author2.persist();

    // Find all and check there is two
    assertEquals(2, Author.count());
    assertEquals(2, Author.listAll().size());

    // Find by id and check it's the right one
    Author foundAuthor1 = Author.findById(author1.id);
    assertNotNull(foundAuthor1);
    assertEquals("Jane", foundAuthor1.firstName);
    assertEquals("Austen", foundAuthor1.lastName);
    assertEquals(Language.ENGLISH, foundAuthor1.preferredLanguage);

    // Update one item, find by id and check it's ok
    foundAuthor1.bio = "Famous English novelist";
    foundAuthor1.preferredLanguage = Language.SPANISH;
    Author updatedAuthor = Author.findById(author1.id);
    assertEquals("Famous English novelist", updatedAuthor.bio);
    assertEquals(Language.SPANISH, updatedAuthor.preferredLanguage);

    // Delete one item and check there is only one now
    Author.deleteById(author1.id);
    assertEquals(1, Author.count());
    assertNull(Author.findById(author1.id));
    assertNotNull(Author.findById(author2.id));

    // Delete the second item and check findall returns zero
    Author.deleteById(author2.id);
    assertEquals(0, Author.count());
    assertTrue(Author.listAll().isEmpty());
  }
}
