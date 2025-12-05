package com.pluralsight.persistence.activity.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "t_user_activity_log")
public class UserActivityLog extends PanacheEntity {

  @Column(nullable = false)
  public String username;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  public Action action;

  @Column(name = "item_id")
  public Long itemId;

  @Column(name = "search_query", length = 500)
  public String searchQuery;

  @Column(name = "ip_address", length = 45, nullable = false)
  public String ipAddress;

  @Column(name = "user_agent", length = 500, nullable = false)
  public String userAgent;

  @Column(nullable = false)
  public Instant timestamp = Instant.now();
}
