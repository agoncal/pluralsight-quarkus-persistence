package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.supplier.model.Supplier;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SupplierRepository implements PanacheRepository<Supplier> {

  public List<Supplier> findByCountry(String country) {
    return find("country", country).list();
  }

  public Supplier findByCompanyName(String companyName) {
    return find("companyName", companyName).firstResult();
  }

  public List<Supplier> findByCompanyNameContaining(String keyword) {
    return list("lower(companyName) like lower(?1)", "%" + keyword + "%");
  }

  public List<Supplier> findByContactNameContaining(String keyword) {
    return list("lower(contactName) like lower(?1)", "%" + keyword + "%");
  }

  public long countByCountry(String country) {
    return count("country", country);
  }
}
