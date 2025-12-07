package com.pluralsight.persistence.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class Address {

  @NotNull
  @Size(max = 200)
  @Column(length = 200, nullable = false)
  private String street;

  @NotNull
  @Size(max = 100)
  @Column(length = 100, nullable = false)
  private String city;

  @Size(max = 100)
  @Column(length = 100)
  private String state;

  @NotNull
  @Size(max = 20)
  @Column(name = "zip_code", length = 20, nullable = false)
  private String zipCode;

  @NotNull
  @Size(max = 50)
  @Column(length = 50, nullable = false)
  private String country;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
