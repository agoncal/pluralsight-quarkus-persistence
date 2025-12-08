package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Author;
import com.pluralsight.persistence.catalog.model.Book;
import com.pluralsight.persistence.catalog.model.CD;
import com.pluralsight.persistence.catalog.model.Language;
import com.pluralsight.persistence.catalog.model.MusicGenre;
import com.pluralsight.persistence.catalog.model.Musician;
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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CatalogTest {

  private static final String UNIQUE_SUFFIX = UUID.randomUUID().toString().substring(0, 8);

  private static Integer userId;
  private static Integer customerId;
  private static Integer authorId;
  private static Integer bookId;
  private static Integer musician1Id;
  private static Integer musician2Id;
  private static Integer cdId;
  private static Integer purchaseOrderId;

  private static String bookTitle;
  private static String cdTitle;
  private static BigDecimal bookPrice;
  private static BigDecimal cdPrice;

  @Test
  @Order(1)
  void shouldCreateUser() {
    User user = new User();
    user.setUsername("testuser_" + UNIQUE_SUFFIX);
    user.setPassword("password123");
    user.setEmail("testuser_" + UNIQUE_SUFFIX + "@example.com");
    user.setRole(UserRole.CUSTOMER);
    user.setEnabled(true);

    userId = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/api/users")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("username", is(user.getUsername()))
      .body("email", is(user.getEmail()))
      .body("role", is("CUSTOMER"))
      .extract().path("id");
  }

  @Test
  @Order(2)
  void shouldCreateCustomer() {
    Address billingAddress = new Address();
    billingAddress.setStreet("123 Main St");
    billingAddress.setCity("New York");
    billingAddress.setState("NY");
    billingAddress.setZipCode("10001");
    billingAddress.setCountry("USA");

    Address shippingAddress = new Address();
    shippingAddress.setStreet("456 Oak Ave");
    shippingAddress.setCity("Los Angeles");
    shippingAddress.setState("CA");
    shippingAddress.setZipCode("90001");
    shippingAddress.setCountry("USA");

    User userRef = new User();
    userRef.setId(userId.longValue());

    Customer customer = new Customer();
    customer.setFirstName("John");
    customer.setLastName("Doe_" + UNIQUE_SUFFIX);
    customer.setPhone("555-1234");
    customer.setBillingAddress(billingAddress);
    customer.setShippingAddress(shippingAddress);
    customer.setUser(userRef);

    customerId = given()
      .contentType(ContentType.JSON)
      .body(customer)
      .when().post("/api/customers")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("firstName", is("John"))
      .body("lastName", is("Doe_" + UNIQUE_SUFFIX))
      .extract().path("id");
  }

  @Test
  @Order(3)
  void shouldCreateAuthor() {
    Author author = new Author();
    author.firstName = "Jane";
    author.lastName = "Author_" + UNIQUE_SUFFIX;
    author.bio = "A famous writer of classic literature";
    author.dateOfBirth = LocalDate.of(1975, 3, 15);
    author.preferredLanguage = Language.ENGLISH;
    author.website = "https://janeauthor.example.com";

    authorId = given()
      .contentType(ContentType.JSON)
      .body(author)
      .when().post("/api/authors")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("firstName", is("Jane"))
      .body("lastName", is("Author_" + UNIQUE_SUFFIX))
      .body("preferredLanguage", is("ENGLISH"))
      .extract().path("id");
  }

  @Test
  @Order(4)
  void shouldCreateBook() {
    String uniqueIsbn = String.format("%013d", System.nanoTime() % 10000000000000L);
    bookTitle = "The Great Novel " + UNIQUE_SUFFIX;
    bookPrice = new BigDecimal("24.99");

    Book book = new Book();
    book.title = bookTitle;
    book.description = "An epic tale of adventure and discovery";
    book.price = bookPrice;
    book.stock = 100;
    book.isbn = uniqueIsbn;
    book.nbOfPages = 450;
    book.publicationDate = LocalDate.of(2024, 1, 15);
    book.language = Language.ENGLISH;

    bookId = given()
      .contentType(ContentType.JSON)
      .body(book)
      .when().post("/api/books")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("title", is(bookTitle))
      .body("price", is(24.99f))
      .body("stock", is(100))
      .body("isbn", is(uniqueIsbn))
      .body("nbOfPages", is(450))
      .body("language", is("ENGLISH"))
      .extract().path("id");
  }

  @Test
  @Order(5)
  void shouldCreateFirstMusician() {
    Musician musician = new Musician();
    musician.firstName = "John";
    musician.lastName = "Musician1_" + UNIQUE_SUFFIX;
    musician.bio = "Lead guitarist with 20 years of experience";
    musician.dateOfBirth = LocalDate.of(1980, 7, 22);
    musician.stageName = "Johnny Guitar";
    musician.instrument = "Guitar";

    musician1Id = given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("firstName", is("John"))
      .body("stageName", is("Johnny Guitar"))
      .body("instrument", is("Guitar"))
      .extract().path("id");
  }

  @Test
  @Order(6)
  void shouldCreateSecondMusician() {
    Musician musician = new Musician();
    musician.firstName = "Sarah";
    musician.lastName = "Musician2_" + UNIQUE_SUFFIX;
    musician.bio = "Award-winning vocalist";
    musician.dateOfBirth = LocalDate.of(1985, 11, 8);
    musician.stageName = "Sarah Sings";
    musician.instrument = "Vocals";

    musician2Id = given()
      .contentType(ContentType.JSON)
      .body(musician)
      .when().post("/api/musicians")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("firstName", is("Sarah"))
      .body("stageName", is("Sarah Sings"))
      .body("instrument", is("Vocals"))
      .extract().path("id");
  }

  @Test
  @Order(7)
  void shouldCreateCD() {
    String uniqueEan = String.format("%013d", System.nanoTime() % 10000000000000L);
    cdTitle = "Greatest Hits " + UNIQUE_SUFFIX;
    cdPrice = new BigDecimal("19.99");

    CD cd = new CD();
    cd.title = cdTitle;
    cd.description = "A collection of timeless classics";
    cd.price = cdPrice;
    cd.stock = 50;
    cd.ean = uniqueEan;
    cd.musicCompany = "Vintage Records";
    cd.genre = MusicGenre.ROCK;
    cd.releaseDate = LocalDate.of(2024, 6, 1);

    cdId = given()
      .contentType(ContentType.JSON)
      .body(cd)
      .when().post("/api/cds")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("title", is(cdTitle))
      .body("price", is(19.99f))
      .body("stock", is(50))
      .body("ean", is(uniqueEan))
      .body("musicCompany", is("Vintage Records"))
      .body("genre", is("ROCK"))
      .extract().path("id");
  }

  @Test
  @Order(8)
  void shouldCreatePurchaseOrder() {
    Address shippingAddress = new Address();
    shippingAddress.setStreet("789 Delivery Lane");
    shippingAddress.setCity("Chicago");
    shippingAddress.setState("IL");
    shippingAddress.setZipCode("60601");
    shippingAddress.setCountry("USA");

    Customer customerRef = new Customer();
    customerRef.setId(customerId.longValue());

    BigDecimal totalAmount = bookPrice.add(cdPrice.multiply(new BigDecimal("2")));

    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setOrderDate(LocalDateTime.now());
    purchaseOrder.setStatus(OrderStatus.PENDING);
    purchaseOrder.setTotalAmount(totalAmount);
    purchaseOrder.setCustomer(customerRef);
    purchaseOrder.setShippingAddress(shippingAddress);

    purchaseOrderId = given()
      .contentType(ContentType.JSON)
      .body(purchaseOrder)
      .when().post("/api/pos")
      .then()
      .statusCode(201)
      .contentType(ContentType.JSON)
      .body("id", notNullValue())
      .body("status", is("PENDING"))
      .body("totalAmount", is(totalAmount.floatValue()))
      .extract().path("id");
  }

  @Test
  @Order(9)
  void shouldVerifyAllEntitiesWereCreated() {
    // Verify user exists
    given()
      .when().get("/api/users/" + userId)
      .then()
      .statusCode(200)
      .body("id", is(userId));

    // Verify customer exists
    given()
      .when().get("/api/customers/" + customerId)
      .then()
      .statusCode(200)
      .body("id", is(customerId));

    // Verify author exists
    given()
      .when().get("/api/authors/" + authorId)
      .then()
      .statusCode(200)
      .body("id", is(authorId));

    // Verify book exists
    given()
      .when().get("/api/books/" + bookId)
      .then()
      .statusCode(200)
      .body("id", is(bookId));

    // Verify first musician exists
    given()
      .when().get("/api/musicians/" + musician1Id)
      .then()
      .statusCode(200)
      .body("id", is(musician1Id));

    // Verify second musician exists
    given()
      .when().get("/api/musicians/" + musician2Id)
      .then()
      .statusCode(200)
      .body("id", is(musician2Id));

    // Verify CD exists
    given()
      .when().get("/api/cds/" + cdId)
      .then()
      .statusCode(200)
      .body("id", is(cdId));

    // Verify purchase order exists
    given()
      .when().get("/api/pos/" + purchaseOrderId)
      .then()
      .statusCode(200)
      .body("id", is(purchaseOrderId));
  }

  @Test
  @Order(10)
  void shouldCleanupCreatedEntities() {
    // Delete in reverse order of dependencies

    // Delete purchase order
    given()
      .when().delete("/api/pos/" + purchaseOrderId)
      .then()
      .statusCode(204);

    // Delete CD
    given()
      .when().delete("/api/cds/" + cdId)
      .then()
      .statusCode(204);

    // Delete musicians
    given()
      .when().delete("/api/musicians/" + musician2Id)
      .then()
      .statusCode(204);

    given()
      .when().delete("/api/musicians/" + musician1Id)
      .then()
      .statusCode(204);

    // Delete book
    given()
      .when().delete("/api/books/" + bookId)
      .then()
      .statusCode(204);

    // Delete author
    given()
      .when().delete("/api/authors/" + authorId)
      .then()
      .statusCode(204);

    // Delete customer
    given()
      .when().delete("/api/customers/" + customerId)
      .then()
      .statusCode(204);

    // Delete user
    given()
      .when().delete("/api/users/" + userId)
      .then()
      .statusCode(204);
  }
}
