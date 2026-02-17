package com.pluralsight.persistence.supplier.model;

import java.time.Instant;

public class Supplier {

  private Long id;

  private String companyName;

  private String contactName;

  private String contactEmail;

  private String country;

  private Instant createdDate = Instant.now();

  public Supplier() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return "Supplier{" +
      "id=" + id +
      ", companyName='" + companyName + '\'' +
      ", contactName='" + contactName + '\'' +
      ", country='" + country + '\'' +
      '}';
  }
}
