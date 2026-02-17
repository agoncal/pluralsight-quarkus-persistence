package com.pluralsight.persistence.customer.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class ToStringTest {

  @Test
  void shouldDisplayCustomer() {
    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setPhone("555-1234");

    String result = customer.toString();

    assertTrue(result.startsWith("Customer{"));
    assertTrue(result.contains("id=1"));
    assertTrue(result.contains("firstName='John'"));
    assertTrue(result.contains("lastName='Doe'"));
    assertTrue(result.contains("phone='555-1234'"));
  }

  @Test
  void shouldDisplayUser() {
    User user = new User();
    user.setId(2L);
    user.setUsername("jdoe");
    user.setEmail("jdoe@example.com");
    user.setRole(UserRole.ADMIN);

    String result = user.toString();

    assertTrue(result.startsWith("User{"));
    assertTrue(result.contains("id=2"));
    assertTrue(result.contains("username='jdoe'"));
    assertTrue(result.contains("email='jdoe@example.com'"));
    assertTrue(result.contains("role=ADMIN"));
  }

  @Test
  void shouldDisplayPurchaseOrder() {
    PurchaseOrder order = new PurchaseOrder();
    order.setId(3L);
    order.setOrderDate(LocalDateTime.of(2025, 1, 15, 10, 30));
    order.setStatus(OrderStatus.PENDING);
    order.setTotalAmount(new BigDecimal("99.99"));

    String result = order.toString();

    assertTrue(result.startsWith("PurchaseOrder{"));
    assertTrue(result.contains("id=3"));
    assertTrue(result.contains("orderDate=2025-01-15T10:30"));
    assertTrue(result.contains("status=PENDING"));
    assertTrue(result.contains("totalAmount=99.99"));
  }

  @Test
  void shouldDisplayOrderLine() {
    OrderLine orderLine = new OrderLine();
    orderLine.setId(4L);
    orderLine.setItem("Effective Java");
    orderLine.setQuantity(2);
    orderLine.setUnitPrice(new BigDecimal("45.99"));

    String result = orderLine.toString();

    assertTrue(result.startsWith("OrderLine{"));
    assertTrue(result.contains("id=4"));
    assertTrue(result.contains("item='Effective Java'"));
    assertTrue(result.contains("quantity=2"));
    assertTrue(result.contains("unitPrice=45.99"));
  }

  @Test
  void shouldDisplayAddress() {
    Address address = new Address();
    address.setStreet("123 Main St");
    address.setCity("Springfield");
    address.setState("IL");
    address.setZipCode("62704");
    address.setCountry("USA");

    String result = address.toString();

    assertTrue(result.startsWith("Address{"));
    assertTrue(result.contains("street='123 Main St'"));
    assertTrue(result.contains("city='Springfield'"));
    assertTrue(result.contains("state='IL'"));
    assertTrue(result.contains("zipCode='62704'"));
    assertTrue(result.contains("country='USA'"));
  }

  @Test
  void shouldDisplayCustomerWithNullFields() {
    Customer customer = new Customer();

    String result = customer.toString();

    assertTrue(result.startsWith("Customer{"));
    assertTrue(result.contains("id=null"));
  }
}
