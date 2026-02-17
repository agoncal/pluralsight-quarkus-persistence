package com.pluralsight.persistence.catalog.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "t_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public abstract class Item extends PanacheEntity {

  @NotNull
  @Size(max = 200)
  @Column(length = 200, nullable = false)
  public String title;

  @Size(max = 3000)
  @Column(length = 3000)
  public String description;

  @NotNull
  @DecimalMin("0.01")
  @Column(nullable = false)
  public BigDecimal price;

  @NotNull
  @Min(0)
  @Column(nullable = false)
  public Integer stock = 0;

  @Column(name = "created_date")
  public Instant createdDate;

  @Column(name = "updated_date")
  public Instant updatedDate;

  @PrePersist
  void prePersist() {
    createdDate = Instant.now();
  }

  @PreUpdate
  void preUpdate() {
    updatedDate = Instant.now();
  }

  // Custom query methods

  public static List<Item> findByTitleContaining(String keyword) {
    return list("lower(title) like lower(?1)", "%" + keyword + "%");
  }

  public static List<Item> findByPriceRange(BigDecimal min, BigDecimal max) {
    return list("price >= ?1 and price <= ?2", min, max);
  }

  public static List<Item> findOutOfStock() {
    return list("stock = 0");
  }

  public static List<Item> findByPriceLessThan(BigDecimal price) {
    return list("price < ?1", price);
  }

  public static List<Item> findCreatedAfter(Instant date) {
    return list("createdDate > ?1", date);
  }

  public static long countInStock() {
    return count("stock > 0");
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", price=" + price +
      ", stock=" + stock +
      '}';
  }
}
