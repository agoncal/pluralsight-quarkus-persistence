package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.supplier.model.Supplier;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
class SupplierResourceTest {

  @Test
  void shouldGetAllSuppliers() {
    given()
      .when().get("/api/suppliers")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentSupplier() {
    given()
      .when().get("/api/suppliers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateSupplier() {
    String uniqueName = "Company" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "John Contact", "john@" + uniqueName.toLowerCase() + ".com", "USA");

    given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().post("/api/suppliers")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("companyName", is(uniqueName))
      .body("contactName", is("John Contact"))
      .body("country", is("USA"));
  }

  @Test
  void shouldCreateAndGetSupplier() {
    String uniqueName = "GetCo" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "Jane Contact", "jane@" + uniqueName.toLowerCase() + ".com", "Canada");

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().post("/api/suppliers")
      .then()
      .statusCode(201)
      .extract().path("id");

    given()
      .when().get("/api/suppliers/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("companyName", is(uniqueName))
      .body("contactName", is("Jane Contact"))
      .body("country", is("Canada"));
  }

  @Test
  void shouldUpdateSupplier() {
    String originalName = "Original" + UUID.randomUUID().toString().substring(0, 8);
    String updatedName = "Updated" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(originalName, "Original Contact", "original@test.com", "USA");

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().post("/api/suppliers")
      .then()
      .statusCode(201)
      .extract().path("id");

    supplier.setCompanyName(updatedName);
    supplier.setContactName("Updated Contact");
    supplier.setContactEmail("updated@test.com");
    supplier.setCountry("UK");

    given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().put("/api/suppliers/" + id)
      .then()
      .statusCode(200)
      .body("companyName", is(updatedName))
      .body("contactName", is("Updated Contact"))
      .body("contactEmail", is("updated@test.com"))
      .body("country", is("UK"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentSupplier() {
    Supplier supplier = createSupplier("NonExistent", "Contact", "contact@test.com", "USA");

    given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().put("/api/suppliers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteSupplier() {
    String uniqueName = "Delete" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "Delete Contact", "delete@test.com", "USA");

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().post("/api/suppliers")
      .then()
      .statusCode(201)
      .extract().path("id");

    given()
      .when().delete("/api/suppliers/" + id)
      .then()
      .statusCode(204);

    given()
      .when().get("/api/suppliers/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentSupplier() {
    given()
      .when().delete("/api/suppliers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutCompanyName() {
    Supplier supplier = new Supplier();
    supplier.setContactName("Contact");
    supplier.setContactEmail("contact@test.com");

    given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().post("/api/suppliers")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithInvalidEmail() {
    Supplier supplier = new Supplier();
    supplier.setCompanyName("Test Company");
    supplier.setContactEmail("invalid-email");

    given()
      .contentType(ContentType.JSON)
      .body(supplier)
      .when().post("/api/suppliers")
      .then()
      .statusCode(400);
  }

  private Supplier createSupplier(String companyName, String contactName, String contactEmail, String country) {
    Supplier supplier = new Supplier();
    supplier.setCompanyName(companyName);
    supplier.setContactName(contactName);
    supplier.setContactEmail(contactEmail);
    supplier.setCountry(country);
    return supplier;
  }
}
