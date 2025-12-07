package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Author;
import com.pluralsight.persistence.catalog.model.Language;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
class AuthorResourceTest {

  @Test
  void shouldGetAllAuthors() {
    given()
      .when().get("/api/authors")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentAuthor() {
    given()
      .when().get("/api/authors/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateAuthor() {
    String uniqueFirstName = "John" + UUID.randomUUID().toString().substring(0, 8);
    String uniqueLastName = "Doe" + UUID.randomUUID().toString().substring(0, 8);
    Author author = new Author();
    author.firstName = uniqueFirstName;
    author.lastName = uniqueLastName;
    author.bio = "A prolific writer";
    author.preferredLanguage = Language.ENGLISH;
    author.website = "https://johndoe.com";

    given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().post("/api/authors")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("firstName", is(uniqueFirstName))
      .body("lastName", is(uniqueLastName))
      .body("bio", is("A prolific writer"))
      .body("preferredLanguage", is("ENGLISH"))
      .body("website", is("https://johndoe.com"));
  }

  @Test
  void shouldCreateAndGetAuthor() {
    String uniqueFirstName = "Jane" + UUID.randomUUID().toString().substring(0, 8);
    String uniqueLastName = "Smith" + UUID.randomUUID().toString().substring(0, 8);
    Author author = new Author();
    author.firstName = uniqueFirstName;
    author.lastName = uniqueLastName;
    author.preferredLanguage = Language.FRENCH;

    // Create an author
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().post("/api/authors")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created author
    given()
      .when().get("/api/authors/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("firstName", is(uniqueFirstName))
      .body("lastName", is(uniqueLastName))
      .body("preferredLanguage", is("FRENCH"));
  }

  @Test
  void shouldUpdateAuthor() {
    String originalFirstName = "Original" + UUID.randomUUID().toString().substring(0, 8);
    String updatedFirstName = "Updated" + UUID.randomUUID().toString().substring(0, 8);
    Author author = new Author();
    author.firstName = originalFirstName;
    author.lastName = "Author";
    author.preferredLanguage = Language.GERMAN;

    // Create an author
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().post("/api/authors")
      .then()
      .statusCode(201)
      .extract().path("id");

    author.firstName = updatedFirstName;
    author.lastName = "UpdatedAuthor";
    author.bio = "Updated bio";
    author.preferredLanguage = Language.SPANISH;
    author.website = "https://updated.com";

    // Update the author
    given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().put("/api/authors/" + id)
      .then()
      .statusCode(200)
      .body("firstName", is(updatedFirstName))
      .body("lastName", is("UpdatedAuthor"))
      .body("bio", is("Updated bio"))
      .body("preferredLanguage", is("SPANISH"))
      .body("website", is("https://updated.com"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentAuthor() {
    Author author = new Author();
    author.firstName = "Non";
    author.lastName = "Existent";

    given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().put("/api/authors/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteAuthor() {
    String uniqueFirstName = "ToDelete" + UUID.randomUUID().toString().substring(0, 8);
    Author author = new Author();
    author.firstName = uniqueFirstName;
    author.lastName = "Author";

    // Create an author
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().post("/api/authors")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the author
    given()
      .when().delete("/api/authors/" + id)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/authors/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentAuthor() {
    given()
      .when().delete("/api/authors/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutFirstName() {
    Author author = new Author();
    author.lastName = "NoFirstName";

    given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().post("/api/authors")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutLastName() {
    Author author = new Author();
    author.firstName = "NoLastName";

    given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().post("/api/authors")
      .then()
      .statusCode(400);
  }
}
