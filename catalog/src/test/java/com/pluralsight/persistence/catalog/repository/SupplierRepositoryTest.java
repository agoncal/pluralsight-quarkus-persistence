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
  void shouldDeleteAllSuppliers() {
    supplierRepository.deleteAll();

    String name1 = "Del1" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier1 = createSupplier(name1, "USA");
    supplierRepository.persist(supplier1);

    String name2 = "Del2" + UUID.randomUUID().toString().substring(0, 8);
    Supplier supplier2 = createSupplier(name2, "Canada");
    supplierRepository.persist(supplier2);

    assertEquals(2, supplierRepository.count());

    supplierRepository.deleteAll();

    assertEquals(0, supplierRepository.count());
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
  void shouldFindSupplierByCompanyNameContaining() {
    supplierRepository.deleteAll();

    Supplier supplier1 = createSupplier("Acme Corporation", "USA");
    supplierRepository.persist(supplier1);

    Supplier supplier2 = createSupplier("Acme Industries", "Canada");
    supplierRepository.persist(supplier2);

    Supplier supplier3 = createSupplier("Global Tech", "UK");
    supplierRepository.persist(supplier3);

    List<Supplier> acmeSuppliers = supplierRepository.findByCompanyNameContaining("acme");
    List<Supplier> techSuppliers = supplierRepository.findByCompanyNameContaining("tech");

    assertEquals(2, acmeSuppliers.size());
    assertEquals(1, techSuppliers.size());
    assertTrue(acmeSuppliers.stream().allMatch(s -> s.getCompanyName().toLowerCase().contains("acme")));
  }

  @Test
  @TestTransaction
  void shouldNotFindSupplierByCompanyNameContaining() {
    supplierRepository.deleteAll();

    Supplier supplier = createSupplier("Test Company", "USA");
    supplierRepository.persist(supplier);

    List<Supplier> suppliers = supplierRepository.findByCompanyNameContaining("nonexistent");

    assertTrue(suppliers.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldFindSupplierByContactNameContaining() {
    supplierRepository.deleteAll();

    Supplier supplier1 = new Supplier();
    supplier1.setCompanyName("Company1" + UUID.randomUUID().toString().substring(0, 4));
    supplier1.setContactName("John Smith");
    supplier1.setContactEmail("john@company1.com");
    supplier1.setCountry("USA");
    supplierRepository.persist(supplier1);

    Supplier supplier2 = new Supplier();
    supplier2.setCompanyName("Company2" + UUID.randomUUID().toString().substring(0, 4));
    supplier2.setContactName("Jane Smith");
    supplier2.setContactEmail("jane@company2.com");
    supplier2.setCountry("UK");
    supplierRepository.persist(supplier2);

    Supplier supplier3 = new Supplier();
    supplier3.setCompanyName("Company3" + UUID.randomUUID().toString().substring(0, 4));
    supplier3.setContactName("Bob Johnson");
    supplier3.setContactEmail("bob@company3.com");
    supplier3.setCountry("Canada");
    supplierRepository.persist(supplier3);

    List<Supplier> smithSuppliers = supplierRepository.findByContactNameContaining("smith");
    List<Supplier> johnsonSuppliers = supplierRepository.findByContactNameContaining("johnson");

    assertEquals(2, smithSuppliers.size());
    assertEquals(1, johnsonSuppliers.size());
    assertTrue(smithSuppliers.stream().allMatch(s -> s.getContactName().toLowerCase().contains("smith")));
  }

  @Test
  @TestTransaction
  void shouldNotFindSupplierByContactNameContaining() {
    supplierRepository.deleteAll();

    Supplier supplier = createSupplier("Test Company", "USA");
    supplierRepository.persist(supplier);

    List<Supplier> suppliers = supplierRepository.findByContactNameContaining("nonexistent");

    assertTrue(suppliers.isEmpty());
  }

  @Test
  @TestTransaction
  void shouldCountSuppliersByCountry() {
    supplierRepository.deleteAll();

    Supplier usSupplier1 = createSupplier("US Company 1", "USA");
    supplierRepository.persist(usSupplier1);

    Supplier usSupplier2 = createSupplier("US Company 2", "USA");
    supplierRepository.persist(usSupplier2);

    Supplier ukSupplier = createSupplier("UK Company", "UK");
    supplierRepository.persist(ukSupplier);

    long usCount = supplierRepository.countByCountry("USA");
    long ukCount = supplierRepository.countByCountry("UK");
    long jpCount = supplierRepository.countByCountry("Japan");

    assertEquals(2, usCount);
    assertEquals(1, ukCount);
    assertEquals(0, jpCount);
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
