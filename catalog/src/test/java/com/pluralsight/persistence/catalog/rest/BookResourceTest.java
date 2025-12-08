package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Book;
import com.pluralsight.persistence.catalog.model.Language;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@QuarkusTest
class BookResourceTest {

  @Test
  void shouldGetAllBooks() {
    given()
      .when().get("/api/books")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentBook() {
    given()
      .when().get("/api/books/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateBook() {
    String uniqueIsbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    Book book = new Book();
    book.title = "Test Book " + UUID.randomUUID().toString().substring(0, 8);
    book.description = "A test book description";
    book.price = new BigDecimal("29.99");
    book.stock = 10;
    book.isbn = uniqueIsbn;
    book.nbOfPages = 350;
    book.publicationDate = LocalDate.of(2023, 6, 15);
    book.language = Language.ENGLISH;

    given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("title", is(book.title))
      .body("description", is("A test book description"))
      .body("price", is(29.99f))
      .body("stock", is(10))
      .body("isbn", is(uniqueIsbn))
      .body("nbOfPages", is(350))
      .body("language", is("ENGLISH"));
  }

  @Test
  void shouldCreateAndGetBook() {
    String uniqueIsbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    Book book = new Book();
    book.title = "Another Test Book " + UUID.randomUUID().toString().substring(0, 8);
    book.price = new BigDecimal("19.99");
    book.stock = 5;
    book.isbn = uniqueIsbn;
    book.nbOfPages = 200;
    book.language = Language.FRENCH;

    // Create a book
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created book
    given()
      .when().get("/api/books/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("title", is(book.title))
      .body("isbn", is(uniqueIsbn))
      .body("nbOfPages", is(200))
      .body("language", is("FRENCH"));
  }

  @Test
  void shouldUpdateBook() {
    String originalIsbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    String updatedIsbn = String.format("%013d", (System.nanoTime() + 1) % 10000000000000L);
    Book book = new Book();
    book.title = "Original Book";
    book.price = new BigDecimal("15.99");
    book.stock = 3;
    book.isbn = originalIsbn;
    book.nbOfPages = 100;
    book.language = Language.GERMAN;

    // Create a book
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(201)
      .extract().path("id");

    book.title = "Updated Book";
    book.description = "Updated description";
    book.price = new BigDecimal("25.99");
    book.stock = 20;
    book.isbn = updatedIsbn;
    book.nbOfPages = 500;
    book.publicationDate = LocalDate.of(2024, 1, 1);
    book.language = Language.SPANISH;

    // Update the book
    given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().put("/api/books/" + id)
      .then()
      .statusCode(200)
      .body("title", is("Updated Book"))
      .body("description", is("Updated description"))
      .body("price", is(25.99f))
      .body("stock", is(20))
      .body("isbn", is(updatedIsbn))
      .body("nbOfPages", is(500))
      .body("language", is("SPANISH"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentBook() {
    Book book = new Book();
    book.title = "Non Existent";
    book.price = new BigDecimal("10.00");
    book.stock = 1;

    given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().put("/api/books/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteBook() {
    String uniqueIsbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    Book book = new Book();
    book.title = "Book To Delete";
    book.price = new BigDecimal("9.99");
    book.stock = 1;
    book.isbn = uniqueIsbn;

    // Create a book
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the book
    given()
      .when().delete("/api/books/" + id)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/books/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentBook() {
    given()
      .when().delete("/api/books/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutTitle() {
    Book book = new Book();
    book.price = new BigDecimal("10.00");
    book.stock = 1;

    given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutPrice() {
    Book book = new Book();
    book.title = "No Price Book";
    book.stock = 1;

    given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithInvalidIsbn() {
    Book book = new Book();
    book.title = "Invalid ISBN Book";
    book.price = new BigDecimal("10.00");
    book.stock = 1;
    book.isbn = "123"; // Too short, should be 13 characters

    given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithNegativePages() {
    Book book = new Book();
    book.title = "Negative Pages Book";
    book.price = new BigDecimal("10.00");
    book.stock = 1;
    book.nbOfPages = 0; // Min is 1

    given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(400);
  }
}
