package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.customer.model.User;
import com.pluralsight.persistence.customer.model.UserRole;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
class UserResourceTest {

  @Test
  void shouldGetAllUsers() {
    given()
      .when().get("/api/users")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentUser() {
    given()
      .when().get("/api/users/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateUser() {
    String uniqueUsername = "user" + UUID.randomUUID().toString().substring(0, 8);
    String uniqueEmail = uniqueUsername + "@example.com";
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueEmail);
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("username", is(uniqueUsername))
      .body("email", is(uniqueEmail))
      .body("role", is("CUSTOMER"))
      .body("enabled", is(true));
  }

  @Test
  void shouldCreateAndGetUser() {
    String uniqueUsername = "getuser" + UUID.randomUUID().toString().substring(0, 8);
    String uniqueEmail = uniqueUsername + "@example.com";
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueEmail);
    user.setRole(UserRole.ADMIN);
    user.setEnabled(true);

    // Create a user
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created user
    given()
      .when().get("/api/users/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("username", is(uniqueUsername))
      .body("email", is(uniqueEmail))
      .body("role", is("ADMIN"));
  }

  @Test
  void shouldUpdateUser() {
    String originalUsername = "original" + UUID.randomUUID().toString().substring(0, 8);
    String updatedUsername = "updated" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(originalUsername);
    user.setPassword("password123");
    user.setEmail(originalUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    // Create a user
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    user.setUsername(updatedUsername);
    user.setPassword("newpassword456");
    user.setEmail(updatedUsername + "@example.com");
    user.setRole(UserRole.MANAGER);
    user.setEnabled(false);

    // Update the user
    given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().put("/api/users/" + id)
      .then()
      .statusCode(200)
      .body("username", is(updatedUsername))
      .body("email", is(updatedUsername + "@example.com"))
      .body("role", is("MANAGER"))
      .body("enabled", is(false));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentUser() {
    User user = new User();
    user.setUsername("nonexistent");
    user.setPassword("password123");
    user.setEmail("nonexistent@example.com");

    given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().put("/api/users/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteUser() {
    String uniqueUsername = "delete" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");

    // Create a user
    Integer id = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the user
    given()
      .when().delete("/api/users/" + id)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/users/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentUser() {
    given()
      .when().delete("/api/users/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutUsername() {
    User user = new User();
    user.setPassword("password123");
    user.setEmail("test@example.com");

    given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutPassword() {
    User user = new User();
    user.setUsername("testuser");
    user.setEmail("test@example.com");

    given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutEmail() {
    User user = new User();
    user.setUsername("testuser");
    user.setPassword("password123");

    given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithInvalidEmail() {
    User user = new User();
    user.setUsername("testuser");
    user.setPassword("password123");
    user.setEmail("invalid-email");

    given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(400);
  }
}
