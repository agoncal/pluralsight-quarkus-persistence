package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.customer.model.Address;
import com.pluralsight.persistence.customer.model.Customer;
import com.pluralsight.persistence.customer.model.OrderStatus;
import com.pluralsight.persistence.customer.model.PurchaseOrder;
import com.pluralsight.persistence.customer.model.User;
import com.pluralsight.persistence.customer.model.UserRole;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@QuarkusTest
class PurchaseOrderResourceTest {

  @Test
  void shouldGetAllPurchaseOrders() {
    given()
      .when().get("/api/pos")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentPurchaseOrder() {
    given()
      .when().get("/api/pos/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreatePurchaseOrder() {
    // First create a user
    String uniqueUsername = "po" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    Integer userId = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Create customer
    user.setId(userId.longValue());
    Customer customer = createCustomer("Order", "Creator", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Now create the purchase order
    customer.setId(customerId.longValue());
    PurchaseOrder purchaseOrder = createPurchaseOrder(customer);

    given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().post("/api/pos")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("status", is("PENDING"))
      .body("totalAmount", is(99.99F));
  }

  @Test
  void shouldCreateAndGetPurchaseOrder() {
    // First create a user
    String uniqueUsername = "get" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    Integer userId = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Create customer
    user.setId(userId.longValue());
    Customer customer = createCustomer("Get", "Order", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Create purchase order
    customer.setId(customerId.longValue());
    PurchaseOrder purchaseOrder = createPurchaseOrder(customer);

    Integer orderId = given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().post("/api/pos")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created purchase order
    given()
      .when().get("/api/pos/" + orderId)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(orderId))
      .body("status", is("PENDING"));
  }

  @Test
  void shouldUpdatePurchaseOrder() {
    // First create a user
    String uniqueUsername = "upd" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    Integer userId = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Create customer
    user.setId(userId.longValue());
    Customer customer = createCustomer("Update", "Order", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Create purchase order
    customer.setId(customerId.longValue());
    PurchaseOrder purchaseOrder = createPurchaseOrder(customer);

    Integer orderId = given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().post("/api/pos")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Update purchase order
    purchaseOrder.setStatus(OrderStatus.CONFIRMED);
    purchaseOrder.setTotalAmount(new BigDecimal("199.99"));

    given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().put("/api/pos/" + orderId)
      .then()
      .statusCode(200)
      .body("status", is("CONFIRMED"))
      .body("totalAmount", is(199.99F));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentPurchaseOrder() {
    // First create a user for the request body
    String uniqueUsername = "nf" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    Integer userId = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    user.setId(userId.longValue());
    Customer customer = createCustomer("NotFound", "Order", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    customer.setId(customerId.longValue());
    PurchaseOrder purchaseOrder = createPurchaseOrder(customer);

    given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().put("/api/pos/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeletePurchaseOrder() {
    // First create a user
    String uniqueUsername = "del" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    Integer userId = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Create customer
    user.setId(userId.longValue());
    Customer customer = createCustomer("Delete", "Order", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Create purchase order
    customer.setId(customerId.longValue());
    PurchaseOrder purchaseOrder = createPurchaseOrder(customer);

    Integer orderId = given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().post("/api/pos")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the purchase order
    given()
      .when().delete("/api/pos/" + orderId)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/pos/" + orderId)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentPurchaseOrder() {
    given()
      .when().delete("/api/pos/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutOrderDate() {
    // First create a user
    String uniqueUsername = "v1" + UUID.randomUUID().toString().substring(0, 8);
    User user = new User();
    user.setUsername(uniqueUsername);
    user.setPassword("password123");
    user.setEmail(uniqueUsername + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    Integer userId = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .extract().path("id");

    user.setId(userId.longValue());
    Customer customer = createCustomer("NoDate", "Order", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    customer.setId(customerId.longValue());
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setCustomer(customer);
    purchaseOrder.setStatus(OrderStatus.PENDING);
    // orderDate is null - should fail validation

    given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().post("/api/pos")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutCustomer() {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setOrderDate(LocalDateTime.now());
    purchaseOrder.setStatus(OrderStatus.PENDING);
    // customer is null - should fail validation

    given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().post("/api/pos")
      .then()
      .statusCode(400);
  }

  private Customer createCustomer(String firstName, String lastName, User user) {
    Customer customer = new Customer();
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setPhone("+1-555-123-4567");
    customer.setUser(user);

    Address billingAddress = new Address();
    billingAddress.setStreet("123 Main St");
    billingAddress.setCity("New York");
    billingAddress.setState("NY");
    billingAddress.setZipCode("10001");
    billingAddress.setCountry("USA");
    customer.setBillingAddress(billingAddress);

    Address shippingAddress = new Address();
    shippingAddress.setStreet("456 Oak Ave");
    shippingAddress.setCity("Los Angeles");
    shippingAddress.setState("CA");
    shippingAddress.setZipCode("90001");
    shippingAddress.setCountry("USA");
    customer.setShippingAddress(shippingAddress);

    return customer;
  }

  private PurchaseOrder createPurchaseOrder(Customer customer) {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setOrderDate(LocalDateTime.now());
    purchaseOrder.setStatus(OrderStatus.PENDING);
    purchaseOrder.setTotalAmount(new BigDecimal("99.99"));
    purchaseOrder.setCustomer(customer);

    Address shippingAddress = new Address();
    shippingAddress.setStreet("789 Shipping St");
    shippingAddress.setCity("Chicago");
    shippingAddress.setState("IL");
    shippingAddress.setZipCode("60601");
    shippingAddress.setCountry("USA");
    purchaseOrder.setShippingAddress(shippingAddress);

    return purchaseOrder;
  }
}
