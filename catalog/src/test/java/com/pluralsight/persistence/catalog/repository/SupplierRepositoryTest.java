package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.supplier.model.Supplier;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

@QuarkusTest
class SupplierRepositoryTest {

  @Inject
  SupplierRepository supplierRepository;

  private Supplier createSupplier(String companyName, String country) {
    Supplier supplier = new Supplier();
    supplier.setCompanyName(companyName);
    supplier.setContactName("Contact " + companyName);
    supplier.setContactEmail(companyName.toLowerCase().replace(" ", "") + "@example.com");
    supplier.setCountry(country);
    return supplier;
  }

  @Test
  @TestTransaction
  void shouldCreateSupplier() {
    String uniqueName = "Company" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "USA");

    supplierRepository.persist(supplier);

    assertNotNull(supplier.getId());
  }

  @Test
  @TestTransaction
  void shouldFindSupplierById() {
    String uniqueName = "Find" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "France");
    supplierRepository.persist(supplier);

    Supplier found = supplierRepository.findById(supplier.getId());

    assertNotNull(found);
    assertEquals(uniqueName, found.getCompanyName());
    assertEquals("France", found.getCountry());
  }

  @Test
  @TestTransaction
  void shouldNotFindSupplierById() {
    Supplier found = supplierRepository.findById(999999L);
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldDeleteSupplier() {
    String uniqueName = "Delete" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "Spain");
    supplierRepository.persist(supplier);

    Long supplierId = supplier.getId();
    assertNotNull(supplierRepository.findById(supplierId));

    boolean deleted = supplierRepository.deleteById(supplierId);

    assertTrue(deleted);
    assertNull(supplierRepository.findById(supplierId));
  }

  @Test
  @TestTransaction
  void shouldNotDeleteNonExistingSupplier() {
    boolean deleted = supplierRepository.deleteById(999999L);
    assertFalse(deleted);
  }

  @Test
  @TestTransaction
  void shouldCountSuppliers() {
    long initialCount = supplierRepository.count();

    String uniqueName = "Count" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "UK");
    supplierRepository.persist(supplier);

    assertEquals(initialCount + 1, supplierRepository.count());
  }

  @Test
  @TestTransaction
  void shouldListAllSuppliers() {
    long initialCount = supplierRepository.count();

    String name1 = "List1" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier1 = createSupplier(name1, "USA");
    supplierRepository.persist(supplier1);

    String name2 = "List2" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier2 = createSupplier(name2, "Canada");
    supplierRepository.persist(supplier2);

    List<Supplier> suppliers = supplierRepository.listAll();

    assertEquals(initialCount + 2, suppliers.size());
  }

  @Test
  @TestTransaction
  void shouldFindSupplierByCountry() {
    String uniqueCountry = "TestCountry" + UUID.randomUUID().toString().substring(0, 4);

    Supplier supplier1 = createSupplier("Company1" + UUID.randomUUID().toString().substring(0, 4), uniqueCountry);
    supplierRepository.persist(supplier1);

    Supplier supplier2 = createSupplier("Company2" + UUID.randomUUID().toString().substring(0, 4), uniqueCountry);
    supplierRepository.persist(supplier2);

    List<Supplier> suppliers = supplierRepository.findByCountry(uniqueCountry);

    assertEquals(2, suppliers.size());
    assertTrue(suppliers.stream().allMatch(s -> s.getCountry().equals(uniqueCountry)));
  }

  @Test
  @TestTransaction
  void shouldNotFindSupplierByCountry() {
    supplierRepository.deleteAll();

    List<Supplier> suppliers = supplierRepository.findByCountry("NonExistentCountry");

    assertTrue(suppliers.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindSupplierByCompanyName() {
    String uniqueName = "UniqueCompany" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier = createSupplier(uniqueName, "Germany");
    supplierRepository.persist(supplier);

    Supplier found = supplierRepository.findByCompanyName(uniqueName);

    assertNotNull(found);
    assertEquals(uniqueName, found.getCompanyName());
    assertEquals("Germany", found.getCountry());
  }

  @Test
  @TestTransaction
  void shouldNotFindSupplierByCompanyName() {
    Supplier found = supplierRepository.findByCompanyName("NonExistentCompany_XYZ_123");
    assertNull(found);
  }

  @Test
  @TestTransaction
  void shouldPerformFullCRUDOnSupplier() {
    // Clear all suppliers
    supplierRepository.deleteAll();

    // Find all and check there is zero
    assertEquals(0, supplierRepository.count());
    assertTrue(supplierRepository.listAll().isEmpty());

    // Create two items
    String name1 = "First" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier1 = createSupplier(name1, "USA");
    supplierRepository.persist(supplier1);

    String name2 = "Second" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier2 = createSupplier(name2, "Canada");
    supplierRepository.persist(supplier2);

    // Find all and check there is two
    assertEquals(2, supplierRepository.count());
    assertEquals(2, supplierRepository.listAll().size());

    // Find by id and check it's the right one
    Supplier foundSupplier1 = supplierRepository.findById(supplier1.getId());
    assertNotNull(foundSupplier1);
    assertEquals(name1, foundSupplier1.getCompanyName());
    assertEquals("USA", foundSupplier1.getCountry());

    // Update one item, find by id and check it's ok
    foundSupplier1.setContactName("New Contact");
    foundSupplier1.setCountry("Mexico");
    Supplier updatedSupplier = supplierRepository.findById(supplier1.getId());
    assertEquals("New Contact", updatedSupplier.getContactName());
    assertEquals("Mexico", updatedSupplier.getCountry());

    // Delete one item and check there is only one now
    supplierRepository.deleteById(supplier1.getId());
    assertEquals(1, supplierRepository.count());
    assertNull(supplierRepository.findById(supplier1.getId()));
    assertNotNull(supplierRepository.findById(supplier2.getId()));

    // Delete the second item and check findall returns zero
    supplierRepository.deleteById(supplier2.getId());
    assertEquals(0, supplierRepository.count());
    assertTrue(supplierRepository.listAll().isEmpty());
  }
}
