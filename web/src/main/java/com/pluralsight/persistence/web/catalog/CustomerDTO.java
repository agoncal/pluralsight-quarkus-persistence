package com.pluralsight.persistence.web.catalog;

import java.time.Instant;

public class CustomerDTO {

  public Long id;
  public String firstName;
  public String lastName;
  public String phone;
  public AddressDTO billingAddress;
  public AddressDTO shippingAddress;
  public UserDTO user;
  public Instant createdDate;
}
