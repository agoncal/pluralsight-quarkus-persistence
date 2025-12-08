package com.pluralsight.persistence.activity.rest;

import com.pluralsight.persistence.activity.model.Action;
import com.pluralsight.persistence.activity.model.UserActivityLog;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class UserActivityResourceTest {

  @Test
  void shouldGetAllActivities() {
    given()
      .when().get("/api/activities")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentActivity() {
    given()
      .when().get("/api/activities/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateActivity() {
    UserActivityLog activity = createTestActivity();

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("username", is(activity.username))
      .body("action", is(activity.action.name()))
      .body("item", is(activity.item))
      .body("ipAddress", is(activity.ipAddress))
      .body("userAgent", is(activity.userAgent));
  }

  @Test
  void shouldCreateAndGetActivity() {
    UserActivityLog activity = createTestActivity();

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(201)
      .extract().path("id");

    given()
      .when().get("/api/activities/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("username", is(activity.username))
      .body("action", is(activity.action.name()))
      .body("item", is(activity.item));
  }

  @Test
  void shouldUpdateActivity() {
    UserActivityLog activity = createTestActivity();

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(201)
      .extract().path("id");

    activity.username = "updateduser";
    activity.action = Action.PLACED_ORDER;
    activity.item = "Updated Item";
    activity.searchQuery = "updated query";
    activity.ipAddress = "10.0.0.1";
    activity.userAgent = "UpdatedAgent/2.0";

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().put("/api/activities/" + id)
      .then()
      .statusCode(200)
      .body("username", is("updateduser"))
      .body("action", is("PLACED_ORDER"))
      .body("item", is("Updated Item"))
      .body("searchQuery", is("updated query"))
      .body("ipAddress", is("10.0.0.1"))
      .body("userAgent", is("UpdatedAgent/2.0"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentActivity() {
    UserActivityLog activity = createTestActivity();

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().put("/api/activities/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteActivity() {
    UserActivityLog activity = createTestActivity();

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(201)
      .extract().path("id");

    given()
      .when().delete("/api/activities/" + id)
      .then()
      .statusCode(204);

    given()
      .when().get("/api/activities/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentActivity() {
    given()
      .when().delete("/api/activities/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldGetActivitiesByUsername() {
    String uniqueUsername = "testuser_" + UUID.randomUUID().toString().substring(0, 8);

    UserActivityLog activity1 = createTestActivity();
    activity1.username = uniqueUsername;

    UserActivityLog activity2 = createTestActivity();
    activity2.username = uniqueUsername;
    activity2.action = Action.ADDED_TO_CART;

    given()
      .contentType(ContentType.JSON)
      .body(activity1)
      .when().post("/api/activities")
      .then()
      .statusCode(201);

    given()
      .contentType(ContentType.JSON)
      .body(activity2)
      .when().post("/api/activities")
      .then()
      .statusCode(201);

    given()
      .when().get("/api/activities/user/" + uniqueUsername)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("size()", greaterThanOrEqualTo(2));
  }

  @Test
  void shouldGetActivitiesByAction() {
    UserActivityLog activity = createTestActivity();
    activity.action = Action.WROTE_REVIEW;

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(201);

    given()
      .when().get("/api/activities/action/WROTE_REVIEW")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("size()", greaterThanOrEqualTo(1));
  }

  @Test
  void shouldFailValidationWithoutUsername() {
    UserActivityLog activity = new UserActivityLog();
    activity.action = Action.VIEWED_ITEM;
    activity.item = "Test Item";
    activity.ipAddress = "192.168.1.1";
    activity.userAgent = "TestAgent/1.0";

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutAction() {
    UserActivityLog activity = new UserActivityLog();
    activity.username = "testuser";
    activity.item = "Test Item";
    activity.ipAddress = "192.168.1.1";
    activity.userAgent = "TestAgent/1.0";

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutItem() {
    UserActivityLog activity = new UserActivityLog();
    activity.username = "testuser";
    activity.action = Action.VIEWED_ITEM;
    activity.ipAddress = "192.168.1.1";
    activity.userAgent = "TestAgent/1.0";

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithUsernameTooLong() {
    UserActivityLog activity = createTestActivity();
    activity.username = "a".repeat(51);

    given()
      .contentType(ContentType.JSON)
      .body(activity)
      .when().post("/api/activities")
      .then()
      .statusCode(400);
  }

  private UserActivityLog createTestActivity() {
    UserActivityLog activity = new UserActivityLog();
    activity.username = "testuser_" + UUID.randomUUID().toString().substring(0, 8);
    activity.action = Action.VIEWED_ITEM;
    activity.item = "Test Item " + UUID.randomUUID().toString().substring(0, 8);
    activity.searchQuery = "test search";
    activity.ipAddress = "192.168.1.1";
    activity.userAgent = "TestAgent/1.0";
    activity.timestamp = Instant.now();
    return activity;
  }
}
