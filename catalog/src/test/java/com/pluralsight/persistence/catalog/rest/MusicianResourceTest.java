package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Musician;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
class MusicianResourceTest {

  @Test
  void shouldGetAllMusicians() {
    given()
      .when().get("/api/musicians")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentMusician() {
    given()
      .when().get("/api/musicians/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateMusician() {
    String uniqueFirstName = "John" + UUID.randomUUID().toString().substring(0, 8);
    String uniqueLastName = "Doe" + UUID.randomUUID().toString().substring(0, 8);
    Musician musician = new Musician();
    musician.firstName = uniqueFirstName;
    musician.lastName = uniqueLastName;
    musician.bio = "A talented musician";
    musician.stageName = "Johnny D";
    musician.instrument = "Guitar";

    given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("firstName", is(uniqueFirstName))
      .body("lastName", is(uniqueLastName))
      .body("bio", is("A talented musician"))
      .body("stageName", is("Johnny D"))
      .body("instrument", is("Guitar"));
  }

  @Test
  void shouldCreateAndGetMusician() {
    String uniqueFirstName = "Jane" + UUID.randomUUID().toString().substring(0, 8);
    String uniqueLastName = "Smith" + UUID.randomUUID().toString().substring(0, 8);
    Musician musician = new Musician();
    musician.firstName = uniqueFirstName;
    musician.lastName = uniqueLastName;
    musician.instrument = "Piano";

    // Create a musician
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created musician
    given()
      .when().get("/api/musicians/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("firstName", is(uniqueFirstName))
      .body("lastName", is(uniqueLastName))
      .body("instrument", is("Piano"));
  }

  @Test
  void shouldUpdateMusician() {
    String originalFirstName = "Original" + UUID.randomUUID().toString().substring(0, 8);
    String updatedFirstName = "Updated" + UUID.randomUUID().toString().substring(0, 8);
    Musician musician = new Musician();
    musician.firstName = originalFirstName;
    musician.lastName = "Musician";
    musician.instrument = "Drums";

    // Create a musician
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(201)
      .extract().path("id");

    musician.firstName = updatedFirstName;
    musician.lastName = "UpdatedMusician";
    musician.bio = "Updated bio";
    musician.stageName = "The Updated One";
    musician.instrument = "Bass";

    // Update the musician
    given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().put("/api/musicians/" + id)
      .then()
      .statusCode(200)
      .body("firstName", is(updatedFirstName))
      .body("lastName", is("UpdatedMusician"))
      .body("bio", is("Updated bio"))
      .body("stageName", is("The Updated One"))
      .body("instrument", is("Bass"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentMusician() {
    Musician musician = new Musician();
    musician.firstName = "Non";
    musician.lastName = "Existent";

    given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().put("/api/musicians/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteMusician() {
    String uniqueFirstName = "ToDelete" + UUID.randomUUID().toString().substring(0, 8);
    Musician musician = new Musician();
    musician.firstName = uniqueFirstName;
    musician.lastName = "Musician";

    // Create a musician
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the musician
    given()
      .when().delete("/api/musicians/" + id)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/musicians/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentMusician() {
    given()
      .when().delete("/api/musicians/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutFirstName() {
    Musician musician = new Musician();
    musician.lastName = "NoFirstName";

    given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutLastName() {
    Musician musician = new Musician();
    musician.firstName = "NoLastName";

    given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(400);
  }
}
