package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.customer.model.OrderStatus;
import com.pluralsight.persistence.customer.model.PurchaseOrder;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PurchaseOrderRepository implements PanacheRepository<PurchaseOrder> {

  public List<PurchaseOrder> findByCustomerId(Long customerId) {
    return find("customer.id", customerId).list();
  }

  public List<PurchaseOrder> findByStatus(OrderStatus status) {
    return find("status", status).list();
  }

  public List<PurchaseOrder> findByUsername(String username) {
    return find("customer.user.username", username).list();
  }

  public List<PurchaseOrder> findByOrderDateBetween(LocalDateTime start, LocalDateTime end) {
    return list("orderDate >= ?1 and orderDate <= ?2", start, end);
  }

  public List<PurchaseOrder> findRecentOrders(int days) {
    LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
    return list("orderDate >= ?1", cutoffDate);
  }
}
