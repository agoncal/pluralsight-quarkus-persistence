package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Publisher;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
class PublisherResourceTest {

  @Test
  void shouldGetAllPublishers() {
    given()
      .when().get("/api/publishers")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentPublisher() {
    given()
      .when().get("/api/publishers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreatePublisher() {
    String uniqueName = "Test Publisher " + UUID.randomUUID();
    Publisher publisher = new Publisher();
    publisher.name = uniqueName;
    publisher.country = "USA";
    publisher.website = "https://testpublisher.com";
    publisher.foundedYear = 2020;

    given()
      .contentType(ContentType.JSON)
      .body(publisher)
      .when().post("/api/publishers")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("name", is(uniqueName))
      .body("country", is("USA"))
      .body("website", is("https://testpublisher.com"))
      .body("foundedYear", is(2020));
  }

  @Test
  void shouldCreateAndGetPublisher() {
    String uniqueName = "Get Test Publisher " + UUID.randomUUID();
    Publisher publisher = new Publisher();
    publisher.name = uniqueName;
    publisher.country = "France";

    // Create a publisher
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(publisher)
      .when().post("/api/publishers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created publisher
    given()
      .when().get("/api/publishers/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("name", is(uniqueName))
      .body("country", is("France"));
  }

  @Test
  void shouldUpdatePublisher() {
    String originalName = "Original Publisher " + UUID.randomUUID();
    String updatedName = "Updated Publisher " + UUID.randomUUID();
    Publisher publisher = new Publisher();
    publisher.name = originalName;
    publisher.country = "Germany";

    // Create a publisher
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(publisher)
      .when().post("/api/publishers")
      .then()
      .statusCode(201)
      .extract().path("id");

    publisher.name = updatedName;
    publisher.country = "Spain";
    publisher.website = "https://updated.com";
    publisher.foundedYear = 1999;

    // Update the publisher
    given()
      .contentType(ContentType.JSON)
      .body(publisher)
      .when().put("/api/publishers/" + id)
      .then()
      .statusCode(200)
      .body("name", is(updatedName))
      .body("country", is("Spain"))
      .body("website", is("https://updated.com"))
      .body("foundedYear", is(1999));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentPublisher() {
    Publisher publisher = new Publisher();
    publisher.name = "Non Existent";
    publisher.country = "Nowhere";

    given()
      .contentType(ContentType.JSON)
      .body(publisher)
      .when().put("/api/publishers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeletePublisher() {
    String uniqueName = "To Be Deleted " + UUID.randomUUID();
    Publisher publisher = new Publisher();
    publisher.name = uniqueName;
    publisher.country = "Italy";

    // Create a publisher
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(publisher)
      .when().post("/api/publishers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the publisher
    given()
      .when().delete("/api/publishers/" + id)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/publishers/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentPublisher() {
    given()
      .when().delete("/api/publishers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutName() {
    Publisher publisher = new Publisher();
    publisher.country = "UK";

    given()
      .contentType(ContentType.JSON)
      .body(publisher)
      .when().post("/api/publishers")
      .then()
      .statusCode(400);
  }
}
