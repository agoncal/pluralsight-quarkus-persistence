package com.pluralsight.persistence.web.activity;

import java.time.Instant;

public class UserActivityLogDTO {

  public Long id;
  public String username;
  public Action action;
  public String item;
  public String searchQuery;
  public String ipAddress;
  public String userAgent;
  public Instant timestamp;
}
