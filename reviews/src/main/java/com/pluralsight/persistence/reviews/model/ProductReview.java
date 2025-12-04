package com.pluralsight.persistence.reviews.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.Instant;

@MongoEntity(collection = "product_reviews")
public class ProductReview extends PanacheMongoEntity {

    public Long itemId;

    public String username;

    public Integer rating;

    public String title;

    public String comment;

    public Integer helpfulCount = 0;

    public Boolean verifiedPurchase = false;

    public Instant createdDate;
}
