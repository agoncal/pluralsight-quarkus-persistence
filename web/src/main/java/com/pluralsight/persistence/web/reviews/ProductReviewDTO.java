package com.pluralsight.persistence.web.reviews;

import java.time.Instant;

public class ProductReviewDTO {

  public Long id;
  public String username;
  public Integer rating;
  public String title;
  public String comment;
  public Instant createdDate;
}
