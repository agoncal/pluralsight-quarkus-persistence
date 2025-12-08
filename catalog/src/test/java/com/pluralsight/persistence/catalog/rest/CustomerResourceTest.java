package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.customer.model.Address;
import com.pluralsight.persistence.customer.model.Customer;
import com.pluralsight.persistence.customer.model.User;
import com.pluralsight.persistence.customer.model.UserRole;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
class CustomerResourceTest {

  @Test
  void shouldGetAllCustomers() {
    given()
      .when().get("/api/customers")
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON);
  }

  @Test
  void shouldGetNotFoundForNonExistentCustomer() {
    given()
      .when().get("/api/customers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldCreateCustomer() {
    // First create a user
    String uniqueUsername = "cust" + UUID.randomUUID().toString().substring(0, 8);
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

    // Now create the customer
    user.setId(userId.longValue());
    Customer customer = createCustomer("John", "Doe", user);

    given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("firstName", is("John"))
      .body("lastName", is("Doe"))
      .body("phone", is("+1-555-123-4567"));
  }

  @Test
  void shouldCreateAndGetCustomer() {
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
    Customer customer = createCustomer("Jane", "Smith", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Get the created customer
    given()
      .when().get("/api/customers/" + customerId)
      .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("id", is(customerId))
      .body("firstName", is("Jane"))
      .body("lastName", is("Smith"));
  }

  @Test
  void shouldUpdateCustomer() {
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
    Customer customer = createCustomer("Original", "Name", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Update customer
    customer.setFirstName("Updated");
    customer.setLastName("Customer");
    customer.setPhone("+1-555-999-8888");

    given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().put("/api/customers/" + customerId)
      .then()
      .statusCode(200)
      .body("firstName", is("Updated"))
      .body("lastName", is("Customer"))
      .body("phone", is("+1-555-999-8888"));
  }

  @Test
  void shouldReturnNotFoundWhenUpdatingNonExistentCustomer() {
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
    Customer customer = createCustomer("Non", "Existent", user);

    given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().put("/api/customers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldDeleteCustomer() {
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
    Customer customer = createCustomer("ToDelete", "Customer", user);

    Integer customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .extract().path("id");

    // Delete the customer
    given()
      .when().delete("/api/customers/" + customerId)
      .then()
      .statusCode(204);

    // Verify it's deleted
    given()
      .when().get("/api/customers/" + customerId)
      .then()
      .statusCode(404);
  }

  @Test
  void shouldReturnNotFoundWhenDeletingNonExistentCustomer() {
    given()
      .when().delete("/api/customers/999999")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldFailValidationWithoutFirstName() {
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
    Customer customer = new Customer();
    customer.setLastName("NoFirstName");
    customer.setUser(user);

    given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(400);
  }

  @Test
  void shouldFailValidationWithoutLastName() {
    // First create a user
    String uniqueUsername = "v2" + UUID.randomUUID().toString().substring(0, 8);
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
    Customer customer = new Customer();
    customer.setFirstName("NoLastName");
    customer.setUser(user);

    given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
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
}
