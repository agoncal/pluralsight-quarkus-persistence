package com.pluralsight.persistence.reviews.repository;

import com.pluralsight.persistence.reviews.model.ProductReview;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductReviewRepository {

  @PersistenceContext
  EntityManager em;

  public List<ProductReview> findAll() {
    return em.createQuery("SELECT r FROM ProductReview r", ProductReview.class)
      .getResultList();
  }

  public Optional<ProductReview> findById(Long id) {
    return Optional.ofNullable(em.find(ProductReview.class, id));
  }

  public ProductReview create(ProductReview review) {
    em.persist(review);
    return review;
  }

  public ProductReview update(ProductReview review) {
    return em.merge(review);
  }

  public void delete(ProductReview review) {
    em.remove(em.contains(review) ? review : em.merge(review));
  }

  public void deleteById(Long id) {
    findById(id).ifPresent(this::delete);
  }
}
