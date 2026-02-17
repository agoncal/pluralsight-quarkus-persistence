package com.pluralsight.persistence.supplier.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ToStringTest {

  @Test
  void shouldDisplaySupplier() {
    Supplier supplier = new Supplier();
    supplier.setId(1L);
    supplier.setCompanyName("Acme Corp");
    supplier.setContactName("Jane Smith");
    supplier.setCountry("USA");

    String result = supplier.toString();

    assertTrue(result.startsWith("Supplier{"));
    assertTrue(result.contains("id=1"));
    assertTrue(result.contains("companyName='Acme Corp'"));
    assertTrue(result.contains("contactName='Jane Smith'"));
    assertTrue(result.contains("country='USA'"));
  }

  @Test
  void shouldDisplaySupplierWithNullFields() {
    Supplier supplier = new Supplier();

    String result = supplier.toString();

    assertTrue(result.startsWith("Supplier{"));
    assertTrue(result.contains("id=null"));
  }
}
