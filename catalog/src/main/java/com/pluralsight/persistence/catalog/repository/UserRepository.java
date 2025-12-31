package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.customer.model.User;
import com.pluralsight.persistence.customer.model.UserRole;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

  public User findByUsername(String username) {
    return find("username", username).firstResult();
  }

  public User findByEmail(String email) {
    return find("email", email).firstResult();
  }

  public List<User> findByRole(UserRole role) {
    return list("role", role);
  }

  public List<User> findByEmailContaining(String keyword) {
    return list("lower(email) like lower(?1)", "%" + keyword + "%");
  }

  public long countByRole(UserRole role) {
    return count("role", role);
  }
}
