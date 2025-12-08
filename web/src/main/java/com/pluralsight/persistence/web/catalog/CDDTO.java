package com.pluralsight.persistence.web.catalog;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

public class CDDTO {

  public Long id;
  public String title;
  public String description;
  public BigDecimal price;
  public Integer stock;
  public Instant createdDate;
  public Instant updatedDate;
  public String ean;
  public String musicCompany;
  public MusicGenre genre;
  public Duration totalDuration;
  public LocalDate releaseDate;
}
