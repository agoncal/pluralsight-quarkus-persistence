package com.pluralsight.persistence.web.catalog;

import java.time.Instant;
import java.time.LocalDate;

public class AuthorDTO {

  public Long id;
  public String firstName;
  public String lastName;
  public String bio;
  public LocalDate dateOfBirth;
  public Instant createdDate;
  public Language preferredLanguage;
  public String website;
}
