package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.CD;
import com.pluralsight.persistence.catalog.model.MusicGenre;
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
class CDResourceTest {

  @Test
  void shouldGetAllCDs() {
    given()
      .when().get("/api/cds")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentCD() {
    given()
      .when().get("/api/cds/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateCD() {
    String uniqueEan = String.format("%013d", System.nanoTime() % 10000000000000L);
    CD cd = new CD();
    cd.title = "Test Album " + UUID.randomUUID().toString().substring(0, 8);
    cd.description = "A test album description";
    cd.price = new BigDecimal("19.99");
    cd.stock = 25;
    cd.ean = uniqueEan;
    cd.musicCompany = "Test Records";
    cd.genre = MusicGenre.ROCK;
    cd.releaseDate = LocalDate.of(2023, 6, 15);

    given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("title", is(cd.title))
      .body("description", is("A test album description"))
      .body("price", is(19.99f))
      .body("stock", is(25))
      .body("ean", is(uniqueEan))
      .body("musicCompany", is("Test Records"))
      .body("genre", is("ROCK"));
  }

  @Test
  void shouldCreateAndGetCD() {
    String uniqueEan = String.format("%013d", System.nanoTime() % 10000000000000L);
    CD cd = new CD();
    cd.title = "Another Test Album " + UUID.randomUUID().toString().substring(0, 8);
    cd.price = new BigDecimal("14.99");
    cd.stock = 15;
    cd.ean = uniqueEan;
    cd.musicCompany = "Indie Label";
    cd.genre = MusicGenre.JAZZ;

    // Create a CD
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created CD
    given()
      .when().get("/api/cds/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("title", is(cd.title))
      .body("ean", is(uniqueEan))
      .body("musicCompany", is("Indie Label"))
      .body("genre", is("JAZZ"));
  }

  @Test
  void shouldUpdateCD() {
    String originalEan = String.format("%013d", System.nanoTime() % 10000000000000L);
    String updatedEan = String.format("%013d", (System.nanoTime() + 1) % 10000000000000L);
    CD cd = new CD();
    cd.title = "Original Album";
    cd.price = new BigDecimal("12.99");
    cd.stock = 10;
    cd.ean = originalEan;
    cd.musicCompany = "Original Records";
    cd.genre = MusicGenre.POP;

    // Create a CD
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(201)
      .extract().path("id");

    cd.title = "Updated Album";
    cd.description = "Updated description";
    cd.price = new BigDecimal("24.99");
    cd.stock = 50;
    cd.ean = updatedEan;
    cd.musicCompany = "Updated Records";
    cd.genre = MusicGenre.ELECTRONIC;
    cd.releaseDate = LocalDate.of(2024, 1, 1);

    // Update the CD
    given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().put("/api/cds/" + id)
      .then()
      .statusCode(200)
      .body("title", is("Updated Album"))
      .body("description", is("Updated description"))
      .body("price", is(24.99f))
      .body("stock", is(50))
      .body("ean", is(updatedEan))
      .body("musicCompany", is("Updated Records"))
      .body("genre", is("ELECTRONIC"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentCD() {
    CD cd = new CD();
    cd.title = "Non Existent";
    cd.price = new BigDecimal("10.00");
    cd.stock = 1;

    given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().put("/api/cds/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteCD() {
    String uniqueEan = String.format("%013d", System.nanoTime() % 10000000000000L);
    CD cd = new CD();
    cd.title = "CD To Delete";
    cd.price = new BigDecimal("9.99");
    cd.stock = 1;
    cd.ean = uniqueEan;

    // Create a CD
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the CD
    given()
      .when().delete("/api/cds/" + id)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/cds/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentCD() {
    given()
      .when().delete("/api/cds/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutTitle() {
    CD cd = new CD();
    cd.price = new BigDecimal("10.00");
    cd.stock = 1;

    given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutPrice() {
    CD cd = new CD();
    cd.title = "No Price CD";
    cd.stock = 1;

    given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithInvalidEan() {
    CD cd = new CD();
    cd.title = "Invalid EAN CD";
    cd.price = new BigDecimal("10.00");
    cd.stock = 1;
    cd.ean = "123"; // Too short, should be 13 characters

    given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(400);
  }
}
