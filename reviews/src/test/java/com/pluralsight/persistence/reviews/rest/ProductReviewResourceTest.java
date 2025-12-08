package com.pluralsight.persistence.reviews.rest;

import com.pluralsight.persistence.reviews.model.ProductReview;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class ProductReviewResourceTest {

  @Test
  void shouldGetAllReviews() {
    given()
      .when().get("/api/reviews")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentReview() {
    given()
      .when().get("/api/reviews/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateReview() {
    ProductReview review = new ProductReview();
    review.username = "testuser_" + UUID.randomUUID().toString().substring(0, 8);
    review.rating = 4;
    review.title = "Great product";
    review.comment = "I really enjoyed this product.";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("username", is(review.username))
      .body("rating", is(4))
      .body("title", is("Great product"))
      .body("comment", is("I really enjoyed this product."));
  }

  @Test
  void shouldCreateAndGetReview() {
    ProductReview review = new ProductReview();
    review.username = "getuser_" + UUID.randomUUID().toString().substring(0, 8);
    review.rating = 5;
    review.title = "Excellent!";
    review.comment = "Best purchase ever.";

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(201)
      .extract().path("id");

    given()
      .when().get("/api/reviews/" + id)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(id))
      .body("username", is(review.username))
      .body("rating", is(5))
      .body("title", is("Excellent!"));
  }

  @Test
  void shouldUpdateReview() {
    ProductReview review = new ProductReview();
    review.username = "updateuser";
    review.rating = 3;
    review.title = "Original title";
    review.comment = "Original comment";

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(201)
      .extract().path("id");

    review.username = "updateduser";
    review.rating = 5;
    review.title = "Updated title";
    review.comment = "Updated comment";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().put("/api/reviews/" + id)
      .then()
      .statusCode(200)
      .body("username", is("updateduser"))
      .body("rating", is(5))
      .body("title", is("Updated title"))
      .body("comment", is("Updated comment"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentReview() {
    ProductReview review = new ProductReview();
    review.username = "nonexistent";
    review.rating = 3;
    review.title = "Test";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().put("/api/reviews/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteReview() {
    ProductReview review = new ProductReview();
    review.username = "deleteuser";
    review.rating = 2;
    review.title = "To be deleted";

    Integer id = given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(201)
      .extract().path("id");

    given()
      .when().delete("/api/reviews/" + id)
      .then()
      .statusCode(204);

    given()
      .when().get("/api/reviews/" + id)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentReview() {
    given()
      .when().delete("/api/reviews/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutUsername() {
    ProductReview review = new ProductReview();
    review.rating = 4;
    review.title = "Test title";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutRating() {
    ProductReview review = new ProductReview();
    review.username = "testuser";
    review.title = "Test title";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutTitle() {
    ProductReview review = new ProductReview();
    review.username = "testuser";
    review.rating = 4;

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithRatingTooHigh() {
    ProductReview review = new ProductReview();
    review.username = "testuser";
    review.rating = 6;
    review.title = "Test title";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithRatingTooLow() {
    ProductReview review = new ProductReview();
    review.username = "testuser";
    review.rating = -1;
    review.title = "Test title";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithUsernameTooLong() {
    ProductReview review = new ProductReview();
    review.username = "a".repeat(51);
    review.rating = 4;
    review.title = "Test title";

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithTitleTooLong() {
    ProductReview review = new ProductReview();
    review.username = "testuser";
    review.rating = 4;
    review.title = "a".repeat(101);

    given()
      .contentType(ContentType.JSON)
      .body(review)
      .when().post("/api/reviews")
      .then()
      .statusCode(400);
  }
}
