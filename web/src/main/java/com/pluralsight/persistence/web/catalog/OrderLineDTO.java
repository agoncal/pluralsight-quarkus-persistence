package com.pluralsight.persistence.web.catalog;

import java.math.BigDecimal;

public class OrderLineDTO {

  public Long id;
  public Integer quantity;
  public BigDecimal unitPrice;
  public String item;
}
