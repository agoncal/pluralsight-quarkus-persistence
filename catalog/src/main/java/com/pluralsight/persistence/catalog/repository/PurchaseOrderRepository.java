package com.pluralsight.persistence.catalog.repository;

import com.pluralsight.persistence.customer.model.PurchaseOrder;
import com.pluralsight.persistence.customer.model.OrderStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PurchaseOrderRepository implements PanacheRepository<PurchaseOrder> {

  public List<PurchaseOrder> findByCustomerId(Long customerId) {
    return find("customer.id", customerId).list();
  }

  public List<PurchaseOrder> findByStatus(OrderStatus status) {
    return find("status", status).list();
  }
}
