package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.customer.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

  public List<Customer> findByLastName(String lastName) {
    return find("lastName", lastName).list();
  }

  public Customer findByUserId(Long userId) {
    return find("user.id", userId).firstResult();
  }
}
