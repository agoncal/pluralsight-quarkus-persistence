package com.pluralsight.persistence.web.catalog;

import java.time.Instant;
import java.time.LocalDate;

public class MusicianDTO {

  public Long id;
  public String firstName;
  public String lastName;
  public String bio;
  public LocalDate dateOfBirth;
  public Instant createdDate;
  public String stageName;
  public String instrument;
}
