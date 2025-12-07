package com.pluralsight.persistence.reviews.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "t_reviews")
public class ProductReview extends PanacheEntity {

  @NotNull
  @Size(max = 50)
  @Column(length = 50, nullable = false)
  public String username;

  @NotNull
  @Min(0)
  @Max(5)
  public Integer rating;

  @NotNull
  @Size(max = 100)
  @Column(length = 100, nullable = false)
  public String title;

  @Size(max = 1024)
  @Column(length = 1024)
  public String comment;

  public Instant createdDate =  Instant.now();
}
