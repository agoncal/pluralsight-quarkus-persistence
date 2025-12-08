package com.pluralsight.persistence.web.catalog;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class BookDTO {

  public Long id;
  public String title;
  public String description;
  public BigDecimal price;
  public Integer stock;
  public Instant createdDate;
  public Instant updatedDate;
  public String isbn;
  public Integer nbOfPages;
  public LocalDate publicationDate;
  public Language language;
  public PublisherDTO publisher;
}
