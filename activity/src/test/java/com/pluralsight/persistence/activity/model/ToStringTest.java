package com.pluralsight.persistence.activity.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ToStringTest {

  @Test
  void shouldDisplayUserActivityLog() {
    UserActivityLog log = new UserActivityLog();
    log.id = 1L;
    log.username = "jdoe";
    log.action = Action.VIEWED_ITEM;
    log.item = "Effective Java";

    String result = log.toString();

    assertTrue(result.startsWith("UserActivityLog{"));
    assertTrue(result.contains("id=1"));
    assertTrue(result.contains("username='jdoe'"));
    assertTrue(result.contains("action=VIEWED_ITEM"));
    assertTrue(result.contains("item='Effective Java'"));
  }

  @Test
  void shouldDisplayUserActivityLogWithNullFields() {
    UserActivityLog log = new UserActivityLog();

    String result = log.toString();

    assertTrue(result.startsWith("UserActivityLog{"));
    assertTrue(result.contains("id=null"));
  }
}
