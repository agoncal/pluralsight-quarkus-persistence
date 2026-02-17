package com.pluralsight.persistence.reviews.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ToStringTest {

  @Test
  void shouldDisplayProductReview() {
    ProductReview review = new ProductReview();
    review.id = 1L;
    review.itemType = ItemType.BOOK;
    review.username = "jdoe";
    review.rating = 5;
    review.title = "Great book!";

    String result = review.toString();

    assertTrue(result.startsWith("ProductReview{"));
    assertTrue(result.contains("id=1"));
    assertTrue(result.contains("itemType=BOOK"));
    assertTrue(result.contains("username='jdoe'"));
    assertTrue(result.contains("rating=5"));
    assertTrue(result.contains("title='Great book!'"));
  }

  @Test
  void shouldDisplayProductReviewWithNullFields() {
    ProductReview review = new ProductReview();

    String result = review.toString();

    assertTrue(result.startsWith("ProductReview{"));
    assertTrue(result.contains("id=null"));
  }
}
