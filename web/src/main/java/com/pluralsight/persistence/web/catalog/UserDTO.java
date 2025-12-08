package com.pluralsight.persistence.web.catalog;

import java.time.Instant;

public class UserDTO {

  public Long id;
  public String username;
  public String email;
  public UserRole role;
  public Boolean enabled;
  public Instant lastLoginDate;
  public Instant createdDate;
}
