package com.pluralsight.persistence.web.catalog;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderDTO {

  public Long id;
  public LocalDateTime orderDate;
  public OrderStatus status;
  public BigDecimal totalAmount;
  public CustomerDTO customer;
  public AddressDTO shippingAddress;
  public List<OrderLineDTO> orderLines;
  public Instant createdDate;
  public Instant updatedDate;
}
