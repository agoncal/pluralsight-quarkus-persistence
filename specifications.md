# VintageStore Application Specifications

## Overview

VintageStore is a modern e-commerce platform built with Quarkus that sells books and CDs. The application demonstrates various data persistence patterns and
technologies available in the Quarkus ecosystem.

## Architecture

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                                  BROWSER                                    │
│                              (HTML / JSON)                                  │
└─────────────────────────────────┬───────────────────────────────────────────┘
                                  │
                                  ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                           VINTAGESTORE WEB APP                              │
│                         (Quarkus + Qute + Renarde)                          │
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                         FACADE LAYER                                │    │
│  │  Exposes REST Endpoints and Web Pages for all domains               │    │
│  │  Aggregates data from local entities and remote microservices       │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
│                                                                             │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                              CATALOG                                  │  │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐      │  │
│  │  │  Item   │  │  Book   │  │   CD    │  │ Track   │  │Publisher│      │  │
│  │  └─────────┘  └─────────┘  └─────────┘  └─────────┘  └─────────┘      │  │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐                                │  │
│  │  │ Person  │  │ Author  │  │Musician │                                │  │
│  │  └─────────┘  └─────────┘  └─────────┘                                │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                            E-COMMERCE                                 │  │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐      │  │
│  │  │  User   │  │Customer │  │Purchase │  │OrderLine│  │ Address │      │  │
│  │  │         │  │         │  │ Order   │  │         │  │(embed)  │      │  │
│  │  └─────────┘  └─────────┘  └─────────┘  └─────────┘  └─────────┘      │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                    │                                        │
│                              PostgreSQL                                     │
└────────────────────────────────────┼────────────────────────────────────────┘
                                     │
           ┌─────────────────────────┼─────────────────────────┐
           │                         │                         │
           ▼                         ▼                         ▼
┌─────────────────────┐  ┌─────────────────────┐  ┌─────────────────────┐
│   SUPPLIER MODULE   │  │  PRODUCT REVIEWS    │  │   USER ACTIVITY     │
│    (JAR Library)    │  │   (Microservice)    │  │   (Microservice)    │
│  ┌───────────────┐  │  │  ┌───────────────┐  │  │  ┌───────────────┐  │
│  │   Supplier    │  │  │  │ ProductReview │  │  │  │UserActivityLog│  │
│  │  (orm.xml)    │  │  │  │   (Panache    │  │  │  │  (Hibernate   │  │
│  │               │  │  │  │   MongoDB)    │  │  │  │   Reactive)   │  │
│  └───────────────┘  │  │  └───────────────┘  │  │  └───────────────┘  │
│                     │  │                     │  │                     │
│   No Database       │  │      MongoDB        │  │       MySQL         │
│   (embedded JAR)    │  │                     │  │                     │
└─────────────────────┘  └─────────────────────┘  └─────────────────────┘
```

## Modules

### 1. VintageStore Web Application

The main web application serving as the storefront and acting as a facade that orchestrates calls to other microservices. It exposes both REST endpoints and web
pages for all domains, including remote services.

#### Technical Stack

| Technology                 | Purpose                                                       |
|----------------------------|---------------------------------------------------------------|
| Quarkus                    | Application framework                                         |
| Qute                       | Templating engine for HTML pages                              |
| Renarde                    | Web framework for server-side rendering                       |
| Hibernate ORM with Panache | Data persistence for local entities                           |
| PostgreSQL                 | Relational database                                           |
| Bean Validation            | Entity constraint validation                                  |
| RESTEasy Reactive          | REST endpoints and REST client for microservice communication |

#### Features

- Product catalog browsing (books and CDs)
- User registration and authentication
- Shopping cart management
- Order placement and tracking
- Publisher and artist information pages
- Supplier management (via local database with orm.xml mapping)
- Product reviews display and submission (via Product Reviews microservice)
- User activity tracking and display (via User Activity microservice)
- Search, filtering, sorting, and pagination

#### Entities

##### Catalog Domain

**Item** (Abstract Panache Entity - Single Table Inheritance)

| Attribute   | Type       | Constraints                |
|-------------|------------|----------------------------|
| id          | Long       | Auto-generated             |
| title       | String     | Max 200, not null          |
| description | String     | Max 3000                   |
| price       | BigDecimal | Not null, min 0.01         |
| stock       | Integer    | Not null, default 0, min 0 |
| createdDate | Instant    | Auto-set                   |
| updatedDate | Instant    | Auto-updated               |

**Book** (Extends Item)

| Attribute       | Type           | Constraints      |
|-----------------|----------------|------------------|
| isbn            | String         | 13 chars, unique |
| nbOfPages       | Integer        | Min 1            |
| publicationDate | LocalDate      | Not in future    |
| language        | Language       | Enum             |
| publisher       | Publisher      | Many-to-One      |
| authors         | List\<Author\> | Many-to-Many     |

**CD** (Extends Item)

| Attribute     | Type             | Constraints          |
|---------------|------------------|----------------------|
| ean           | String           | 13 chars, unique     |
| musicCompany  | String           | Max 100              |
| genre         | MusicGenre       | Enum                 |
| totalDuration | Duration         |                      |
| releaseDate   | LocalDate        |                      |
| musicians     | List\<Musician\> | Many-to-Many         |
| tracks        | List\<Track\>    | One-to-Many, ordered |

**Person** (Abstract Panache Entity - Joined Table Inheritance)

| Attribute   | Type      | Constraints      |
|-------------|-----------|------------------|
| id          | Long      | Auto-generated   |
| firstName   | String    | Max 50, not null |
| lastName    | String    | Max 50, not null |
| bio         | String    | Max 3000         |
| dateOfBirth | LocalDate |                  |
| createdDate | Instant   | Auto-set         |

**Author** (Extends Person)

| Attribute         | Type         | Constraints                     |
|-------------------|--------------|---------------------------------|
| preferredLanguage | Language     | Enum                            |
| website           | String       | URL format                      |
| books             | List\<Book\> | Many-to-Many, mapped by authors |

**Musician** (Extends Person)

| Attribute  | Type       | Constraints                       |
|------------|------------|-----------------------------------|
| stageName  | String     | Max 100                           |
| instrument | String     | Max 50                            |
| cds        | List\<CD\> | Many-to-Many, mapped by musicians |

**Publisher** (Panache Entity - Active Record Pattern)

| Attribute   | Type         | Constraints                      |
|-------------|--------------|----------------------------------|
| id          | Long         | Auto-generated                   |
| name        | String       | Max 100, not null, unique        |
| country     | String       | Max 50                           |
| website     | String       | URL format                       |
| foundedYear | Integer      |                                  |
| books       | List\<Book\> | One-to-Many, mapped by publisher |
| createdDate | Instant      | Auto-set                         |

**Track** (Panache Entity)

| Attribute   | Type     | Constraints           |
|-------------|----------|-----------------------|
| id          | Long     | Auto-generated        |
| title       | String   | Max 200, not null     |
| duration    | Duration | Not null              |
| trackNumber | Integer  | Not null, min 1       |
| cd          | CD       | Many-to-One, not null |

##### E-Commerce Domain

**Address** (Embeddable)

| Attribute | Type   | Constraints       |
|-----------|--------|-------------------|
| street    | String | Max 200, not null |
| city      | String | Max 100, not null |
| state     | String | Max 100           |
| zipCode   | String | Max 20, not null  |
| country   | String | Max 50, not null  |

**User** (JPA Entity - Repository Pattern)

| Attribute     | Type     | Constraints                    |
|---------------|----------|--------------------------------|
| id            | Long     | Auto-generated                 |
| username      | String   | Max 50, unique, not null       |
| password      | String   | Hashed, not null               |
| email         | String   | Email format, unique, not null |
| role          | UserRole | Enum                           |
| enabled       | Boolean  | Default true                   |
| lastLoginDate | Instant  |                                |
| createdDate   | Instant  | Auto-set                       |

**Customer** (JPA Entity - Repository Pattern)

| Attribute       | Type    | Constraints          |
|-----------------|---------|----------------------|
| id              | Long    | Auto-generated       |
| firstName       | String  | Max 50, not null     |
| lastName        | String  | Max 50, not null     |
| phone           | String  | Max 20               |
| billingAddress  | Address | Embedded             |
| shippingAddress | Address | Embedded             |
| user            | User    | One-to-One, not null |
| createdDate     | Instant | Auto-set             |

**PurchaseOrder** (JPA Entity - Repository Pattern)

| Attribute       | Type              | Constraints              |
|-----------------|-------------------|--------------------------|
| id              | Long              | Auto-generated           |
| orderDate       | LocalDateTime     | Not null                 |
| status          | OrderStatus       | Enum                     |
| totalAmount     | BigDecimal        | Computed                 |
| customer        | Customer          | Many-to-One, not null    |
| shippingAddress | Address           | Embedded                 |
| orderLines      | List\<OrderLine\> | One-to-Many, cascade all |
| createdDate     | Instant           | Auto-set                 |
| updatedDate     | Instant           | Auto-updated             |

**OrderLine** (JPA Entity)

| Attribute     | Type          | Constraints           |
|---------------|---------------|-----------------------|
| id            | Long          | Auto-generated        |
| quantity      | Integer       | Not null, min 1       |
| unitPrice     | BigDecimal    | Not null              |
| item          | Item          | Many-to-One, not null |
| purchaseOrder | PurchaseOrder | Many-to-One, not null |

##### Supplier Domain (Legacy POJO via orm.xml)

**Supplier** (Plain POJO - mapped via orm.xml)

| Attribute    | Type    | Constraints       |
|--------------|---------|-------------------|
| id           | Long    | Auto-generated    |
| companyName  | String  | Max 100, not null |
| contactName  | String  | Max 100           |
| contactEmail | String  | Email format      |
| country      | String  | Max 50            |
| createdDate  | Instant | Auto-set          |

##### Enumerations

| Enum        | Values                                                                        |
|-------------|-------------------------------------------------------------------------------|
| Language    | ENGLISH, FRENCH, SPANISH, GERMAN, PORTUGUESE, ITALIAN, JAPANESE, CHINESE      |
| MusicGenre  | ROCK, POP, JAZZ, CLASSICAL, ELECTRONIC, HIP_HOP, COUNTRY, BLUES, METAL, OTHER |
| OrderStatus | PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED                             |
| UserRole    | CUSTOMER, ADMIN, MANAGER                                                      |

#### REST Endpoints

##### Catalog Endpoints (Local)

| Method | Path                 | Description                    |
|--------|----------------------|--------------------------------|
| GET    | /api/books           | List all books with pagination |
| GET    | /api/books/{id}      | Get book details               |
| POST   | /api/books           | Create a new book              |
| PUT    | /api/books/{id}      | Update a book                  |
| DELETE | /api/books/{id}      | Delete a book                  |
| GET    | /api/cds             | List all CDs with pagination   |
| GET    | /api/cds/{id}        | Get CD details                 |
| POST   | /api/cds             | Create a new CD                |
| PUT    | /api/cds/{id}        | Update a CD                    |
| DELETE | /api/cds/{id}        | Delete a CD                    |
| GET    | /api/authors         | List all authors               |
| GET    | /api/authors/{id}    | Get author details             |
| POST   | /api/authors         | Create a new author            |
| PUT    | /api/authors/{id}    | Update an author               |
| DELETE | /api/authors/{id}    | Delete an author               |
| GET    | /api/musicians       | List all musicians             |
| GET    | /api/musicians/{id}  | Get musician details           |
| POST   | /api/musicians       | Create a new musician          |
| PUT    | /api/musicians/{id}  | Update a musician              |
| DELETE | /api/musicians/{id}  | Delete a musician              |
| GET    | /api/publishers      | List all publishers            |
| GET    | /api/publishers/{id} | Get publisher details          |
| POST   | /api/publishers      | Create a new publisher         |
| PUT    | /api/publishers/{id} | Update a publisher             |
| DELETE | /api/publishers/{id} | Delete a publisher             |

##### E-Commerce Endpoints (Local)

| Method | Path                       | Description                     |
|--------|----------------------------|---------------------------------|
| GET    | /api/customers             | List all customers              |
| GET    | /api/customers/{id}        | Get customer details            |
| POST   | /api/customers             | Create a new customer           |
| PUT    | /api/customers/{id}        | Update a customer               |
| DELETE | /api/customers/{id}        | Delete a customer               |
| GET    | /api/orders                | List all orders with pagination |
| GET    | /api/orders/{id}           | Get order details               |
| POST   | /api/orders                | Create a new order              |
| PUT    | /api/orders/{id}           | Update an order                 |
| DELETE | /api/orders/{id}           | Delete an order                 |
| GET    | /api/customers/{id}/orders | Get customer orders             |

##### Supplier Endpoints (Local - Legacy)

| Method | Path                             | Description                        |
|--------|----------------------------------|------------------------------------|
| GET    | /api/suppliers                   | List all suppliers with pagination |
| GET    | /api/suppliers/{id}              | Get supplier details               |
| POST   | /api/suppliers                   | Create a new supplier              |
| PUT    | /api/suppliers/{id}              | Update a supplier                  |
| DELETE | /api/suppliers/{id}              | Delete a supplier                  |
| GET    | /api/suppliers/country/{country} | Get suppliers by country           |

##### Product Reviews Endpoints (Facade to Product Reviews Microservice)

| Method | Path                               | Description                      |
|--------|------------------------------------|----------------------------------|
| GET    | /api/reviews                       | List all reviews with pagination |
| GET    | /api/reviews/{id}                  | Get review by ID                 |
| GET    | /api/reviews/item/{itemId}         | Get reviews for an item          |
| GET    | /api/reviews/item/{itemId}/average | Get average rating for an item   |
| GET    | /api/reviews/customer/{customerId} | Get reviews by a customer        |
| POST   | /api/reviews                       | Create a new review              |
| PUT    | /api/reviews/{id}                  | Update a review                  |
| DELETE | /api/reviews/{id}                  | Delete a review                  |
| POST   | /api/reviews/{id}/helpful          | Increment helpful count          |

##### User Activity Endpoints (Facade to User Activity Microservice)

| Method | Path                            | Description                         |
|--------|---------------------------------|-------------------------------------|
| GET    | /api/activities                 | List all activities with pagination |
| GET    | /api/activities/{id}            | Get activity by ID                  |
| GET    | /api/activities/user/{userId}   | Get activities for a user           |
| GET    | /api/activities/action/{action} | Get activities by action type       |
| GET    | /api/activities/item/{itemId}   | Get activities for an item          |
| POST   | /api/activities                 | Record a new activity               |
| DELETE | /api/activities/{id}            | Delete an activity                  |

#### Web Pages (Qute + Renarde)

##### Public Pages

| Page              | Path             | Description                         |
|-------------------|------------------|-------------------------------------|
| Home              | /                | Landing page with featured items    |
| Book List         | /books           | Browsable book catalog with filters |
| Book Details      | /books/{id}      | Single book page with reviews       |
| CD List           | /cds             | Browsable CD catalog with filters   |
| CD Details        | /cds/{id}        | Single CD page with reviews         |
| Author List       | /authors         | List of all authors                 |
| Author Details    | /authors/{id}    | Author page with their books        |
| Musician List     | /musicians       | List of all musicians               |
| Musician Details  | /musicians/{id}  | Musician page with their CDs        |
| Publisher List    | /publishers      | List of all publishers              |
| Publisher Details | /publishers/{id} | Publisher page with their books     |
| Search            | /search          | Search results page                 |

##### Customer Pages

| Page          | Path               | Description                  |
|---------------|--------------------|------------------------------|
| Login         | /login             | User authentication          |
| Register      | /register          | User registration            |
| Profile       | /profile           | User profile management      |
| Cart          | /cart              | Shopping cart                |
| Checkout      | /checkout          | Order placement              |
| Orders        | /orders            | Order history                |
| Order Details | /orders/{id}       | Single order details         |
| My Reviews    | /my-reviews        | Customer's submitted reviews |
| Write Review  | /items/{id}/review | Review submission form       |
| My Activity   | /my-activity       | User's activity history      |

##### Admin Pages

| Page              | Path              | Description              |
|-------------------|-------------------|--------------------------|
| Dashboard         | /admin            | Admin dashboard          |
| Manage Books      | /admin/books      | CRUD for books           |
| Manage CDs        | /admin/cds        | CRUD for CDs             |
| Manage Authors    | /admin/authors    | CRUD for authors         |
| Manage Musicians  | /admin/musicians  | CRUD for musicians       |
| Manage Publishers | /admin/publishers | CRUD for publishers      |
| Manage Suppliers  | /admin/suppliers  | CRUD for suppliers       |
| Manage Customers  | /admin/customers  | Customer management      |
| Manage Orders     | /admin/orders     | Order management         |
| View Reviews      | /admin/reviews    | Review moderation        |
| View Activity     | /admin/activities | User activity monitoring |

#### REST Clients for Product Review Microservices

```java
@RegisterRestClient(configKey = "product-reviews-api")
@Path("/api/reviews")
public interface ProductReviewClient {

    @GET
    List<ProductReviewDTO> getAllReviews(
        @QueryParam("page") int page,
        @QueryParam("size") int size);

    @GET
    @Path("/{id}")
    ProductReviewDTO getReviewById(@PathParam("id") String id);

    @GET
    @Path("/item/{itemId}")
    List<ProductReviewDTO> getReviewsForItem(@PathParam("itemId") Long itemId);

    @GET
    @Path("/item/{itemId}/average")
    Double getAverageRating(@PathParam("itemId") Long itemId);

    @GET
    @Path("/customer/{customerId}")
    List<ProductReviewDTO> getReviewsByCustomer(@PathParam("customerId") Long customerId);

    @POST
    ProductReviewDTO createReview(ProductReviewDTO review);

    @PUT
    @Path("/{id}")
    ProductReviewDTO updateReview(@PathParam("id") String id, ProductReviewDTO review);

    @DELETE
    @Path("/{id}")
    void deleteReview(@PathParam("id") String id);

    @POST
    @Path("/{id}/helpful")
    ProductReviewDTO markHelpful(@PathParam("id") String id);
}
```

#### REST Clients for User Activty Microservices

```java
@RegisterRestClient(configKey = "user-activity-api")
@Path("/api/activities")
public interface UserActivityClient {

    @GET
    Uni<List<UserActivityDTO>> getAllActivities(
        @QueryParam("page") int page,
        @QueryParam("size") int size);

    @GET
    @Path("/{id}")
    Uni<UserActivityDTO> getActivityById(@PathParam("id") Long id);

    @GET
    @Path("/user/{userId}")
    Multi<UserActivityDTO> getActivitiesForUser(@PathParam("userId") Long userId);

    @GET
    @Path("/action/{action}")
    Multi<UserActivityDTO> getActivitiesByAction(@PathParam("action") String action);

    @GET
    @Path("/item/{itemId}")
    Multi<UserActivityDTO> getActivitiesForItem(@PathParam("itemId") Long itemId);

    @POST
    Uni<UserActivityDTO> recordActivity(UserActivityDTO activity);

    @DELETE
    @Path("/{id}")
    Uni<Boolean> deleteActivity(@PathParam("id") Long id);
}
```

---

### 2. Supplier Module (JAR Library)

A standalone JAR file containing legacy POJO entities without JPA annotations. This module demonstrates integration with external legacy code using orm.xml
mapping.

#### Technical Stack

| Technology         | Purpose              |
|--------------------|----------------------|
| Plain Java         | POJO definitions     |
| No JPA annotations | Legacy compatibility |

#### Entities

**Supplier** (Plain POJO - mapped via orm.xml in VintageStore)

| Attribute    | Type    | Description               |
|--------------|---------|---------------------------|
| id           | Long    | Identifier                |
| companyName  | String  | Supplier company name     |
| contactName  | String  | Primary contact person    |
| contactEmail | String  | Contact email address     |
| country      | String  | Country of operation      |
| createdDate  | Instant | Record creation timestamp |

#### Integration with VintageStore

The VintageStore application includes this JAR as a dependency and uses:

- **orm.xml** to map the Supplier POJO as a JPA entity
- **PanacheRepository** to provide data access methods

```xml
<!-- orm.xml mapping -->
<entity-mappings xmlns="http://xmlns.jcp.org/xml/ns/persistence/orm"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence/orm
                 http://xmlns.jcp.org/xml/ns/persistence/orm_2_2.xsd"
                 version="2.2">

    <entity class="org.agoncal.quarkus.supplier.Supplier">
        <table name="t_suppliers"/>
        <attributes>
            <id name="id">
                <generated-value strategy="IDENTITY"/>
            </id>
            <basic name="companyName">
                <column name="company_name" length="100" nullable="false"/>
            </basic>
            <basic name="contactName">
                <column name="contact_name" length="100"/>
            </basic>
            <basic name="contactEmail">
                <column name="contact_email" length="255"/>
            </basic>
            <basic name="country">
                <column name="country" length="50"/>
            </basic>
            <basic name="createdDate">
                <column name="created_date"/>
            </basic>
        </attributes>
    </entity>

</entity-mappings>
```

---

### 3. Product Reviews Microservice

A dedicated microservice for managing product reviews using MongoDB for flexible schema and high read throughput.

#### Technical Stack

| Technology        | Purpose               |
|-------------------|-----------------------|
| Quarkus           | Application framework |
| Panache MongoDB   | Data persistence      |
| MongoDB           | NoSQL database        |
| RESTEasy Reactive | REST API exposure     |
| JSON-B            | JSON serialization    |

#### Features

- Create, read, update, delete product reviews
- Aggregate reviews by product
- Calculate average ratings
- Mark reviews as helpful
- Filter verified purchases

#### Entities

**ProductReview** (Panache MongoDB Entity)

| Attribute        | Type     | Constraints                     |
|------------------|----------|---------------------------------|
| id               | ObjectId | Auto-generated                  |
| itemId           | Long     | Reference to Item, not null     |
| customerId       | Long     | Reference to Customer, not null |
| rating           | Integer  | 1-5, not null                   |
| title            | String   | Max 100                         |
| comment          | String   | Max 2000                        |
| helpfulCount     | Integer  | Default 0                       |
| verifiedPurchase | Boolean  | Default false                   |
| createdDate      | Instant  | Auto-set                        |

#### REST Endpoints

| Method | Path                               | Description                      |
|--------|------------------------------------|----------------------------------|
| GET    | /api/reviews                       | List all reviews with pagination |
| GET    | /api/reviews/{id}                  | Get review by ID                 |
| GET    | /api/reviews/item/{itemId}         | Get reviews for an item          |
| GET    | /api/reviews/item/{itemId}/average | Get average rating for an item   |
| GET    | /api/reviews/customer/{customerId} | Get reviews by a customer        |
| POST   | /api/reviews                       | Create a new review              |
| PUT    | /api/reviews/{id}                  | Update a review                  |
| DELETE | /api/reviews/{id}                  | Delete a review                  |
| POST   | /api/reviews/{id}/helpful          | Increment helpful count          |

#### Sample JSON Response

```json
{
  "id": "507f1f77bcf86cd799439011",
  "itemId": 42,
  "customerId": 15,
  "rating": 5,
  "title": "Excellent book!",
  "comment": "This is one of the best books I've ever read on the subject.",
  "helpfulCount": 23,
  "verifiedPurchase": true,
  "createdDate": "2024-01-15T10:30:00Z"
}
```

---

### 4. User Activity Microservice

A high-throughput microservice for tracking user activity using Hibernate Reactive for non-blocking database operations.

#### Technical Stack

| Technology                      | Purpose                       |
|---------------------------------|-------------------------------|
| Quarkus                         | Application framework         |
| Hibernate Reactive with Panache | Non-blocking data persistence |
| MySQL                           | Relational database           |
| Mutiny                          | Reactive programming          |
| RESTEasy Reactive               | REST API exposure             |
| JSON-B                          | JSON serialization            |

#### Features

- Track user actions in real-time
- Non-blocking database writes for high throughput
- Query activity logs by user, action type, or time range
- Support for analytics and reporting

#### Entities

**UserActivityLog** (Panache Reactive Entity)

| Attribute   | Type           | Constraints                        |
|-------------|----------------|------------------------------------|
| id          | Long           | Auto-generated                     |
| userId      | Long           | Reference to User, not null        |
| action      | ActivityAction | Enum, not null                     |
| itemId      | Long           | Optional, for item-related actions |
| searchQuery | String         | Optional, for search actions       |
| ipAddress   | String         | Max 45 (IPv6 support)              |
| userAgent   | String         | Max 500                            |
| timestamp   | Instant        | Not null                           |

#### Enumeration

| Enum           | Values                                                                                                     |
|----------------|------------------------------------------------------------------------------------------------------------|
| ActivityAction | VIEWED_ITEM, ADDED_TO_CART, REMOVED_FROM_CART, SEARCHED, LOGGED_IN, LOGGED_OUT, PLACED_ORDER, WROTE_REVIEW |

#### REST Endpoints

All endpoints return reactive types (Uni or Multi).

| Method | Path                            | Description                         | Return Type              |
|--------|---------------------------------|-------------------------------------|--------------------------|
| GET    | /api/activities                 | List all activities with pagination | Multi\<UserActivityLog\> |
| GET    | /api/activities/{id}            | Get activity by ID                  | Uni\<UserActivityLog\>   |
| GET    | /api/activities/user/{userId}   | Get activities for a user           | Multi\<UserActivityLog\> |
| GET    | /api/activities/action/{action} | Get activities by action type       | Multi\<UserActivityLog\> |
| GET    | /api/activities/item/{itemId}   | Get activities for an item          | Multi\<UserActivityLog\> |
| POST   | /api/activities                 | Record a new activity               | Uni\<UserActivityLog\>   |
| DELETE | /api/activities/{id}            | Delete an activity                  | Uni\<Boolean\>           |

#### Sample JSON Response

```json
{
  "id": 12345,
  "userId": 42,
  "action": "VIEWED_ITEM",
  "itemId": 101,
  "searchQuery": null,
  "ipAddress": "192.168.1.100",
  "userAgent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
  "timestamp": "2024-01-15T14:22:33Z"
}
```

---

## Database Schema

### PostgreSQL (VintageStore)

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│    t_items      │     │   t_publishers  │     │    t_persons    │
├─────────────────┤     ├─────────────────┤     ├─────────────────┤
│ id (PK)         │     │ id (PK)         │     │ id (PK)         │
│ dtype           │     │ name            │     │ dtype           │
│ title           │     │ country         │     │ first_name      │
│ description     │     │ website         │     │ last_name       │
│ price           │     │ founded_year    │     │ bio             │
│ stock           │     │ created_date    │     │ date_of_birth   │
│ created_date    │     └─────────────────┘     │ created_date    │
│ updated_date    │                             └─────────────────┘
│ isbn            │                                     │
│ nb_of_pages     │     ┌─────────────────┐            │
│ publication_date│     │   t_authors     │◄───────────┤
│ language        │     ├─────────────────┤            │
│ publisher_fk    │────►│ id (PK, FK)     │            │
│ ean             │     │ preferred_lang  │     ┌──────┴──────────┐
│ music_company   │     │ website         │     │   t_musicians   │
│ genre           │     └─────────────────┘     ├─────────────────┤
│ total_duration  │                             │ id (PK, FK)     │
│ release_date    │     ┌─────────────────┐     │ stage_name      │
└─────────────────┘     │ t_book_authors  │     │ instrument      │
        │               ├─────────────────┤     └─────────────────┘
        │               │ book_id (FK)    │
        │               │ author_id (FK)  │
        │               └─────────────────┘
        │
        │               ┌─────────────────┐
        │               │ t_cd_musicians  │
        │               ├─────────────────┤
        │               │ cd_id (FK)      │
        │               │ musician_id (FK)│
        │               └─────────────────┘
        │
        ▼
┌─────────────────┐
│    t_tracks     │
├─────────────────┤
│ id (PK)         │
│ title           │
│ duration        │
│ track_number    │
│ cd_fk (FK)      │
└─────────────────┘

┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│    t_users      │     │   t_customers   │     │t_purchase_orders│
├─────────────────┤     ├─────────────────┤     ├─────────────────┤
│ id (PK)         │◄────│ id (PK)         │◄────│ id (PK)         │
│ username        │     │ first_name      │     │ order_date      │
│ password        │     │ last_name       │     │ status          │
│ email           │     │ phone           │     │ total_amount    │
│ role            │     │ billing_street  │     │ customer_fk     │
│ enabled         │     │ billing_city    │     │ shipping_street │
│ last_login_date │     │ billing_state   │     │ shipping_city   │
│ created_date    │     │ billing_zip     │     │ shipping_state  │
└─────────────────┘     │ billing_country │     │ shipping_zip    │
                        │ shipping_street │     │ shipping_country│
                        │ shipping_city   │     │ created_date    │
                        │ shipping_state  │     │ updated_date    │
                        │ shipping_zip    │     └────────┬────────┘
                        │ shipping_country│              │
                        │ user_fk (FK)    │              │
                        │ created_date    │              ▼
                        └─────────────────┘     ┌─────────────────┐
                                                │ t_order_lines   │
┌─────────────────┐                             ├─────────────────┤
│   t_suppliers   │                             │ id (PK)         │
├─────────────────┤                             │ quantity        │
│ id (PK)         │                             │ unit_price      │
│ company_name    │                             │ item_fk (FK)    │
│ contact_name    │                             │ order_fk (FK)   │
│ contact_email   │                             └─────────────────┘
│ country         │
│ created_date    │
└─────────────────┘
```

### MongoDB (Product Reviews)

```
Collection: product_reviews
{
  "_id": ObjectId,
  "itemId": Long,
  "customerId": Long,
  "rating": Integer,
  "title": String,
  "comment": String,
  "helpfulCount": Integer,
  "verifiedPurchase": Boolean,
  "createdDate": ISODate
}

Indexes:
- itemId (for product lookup)
- customerId (for customer reviews)
- rating (for filtering)
- createdDate (for sorting)
```

### MySQL (User Activity)

```
┌─────────────────────┐
│ t_user_activity_log │
├─────────────────────┤
│ id (PK)             │
│ user_id             │
│ action              │
│ item_id             │
│ search_query        │
│ ip_address          │
│ user_agent          │
│ timestamp           │
└─────────────────────┘

Indexes:
- user_id (for user activity lookup)
- action (for action type filtering)
- item_id (for item activity)
- timestamp (for time-based queries)
```

---

## Configuration

### VintageStore (application.properties)

```properties
# Application
quarkus.application.name=vintagestore

# PostgreSQL DataSource
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=vintage
quarkus.datasource.password=vintage
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/vintagestore

# Hibernate ORM
quarkus.hibernate-orm.database.generation=update
quarkus.hibernate-orm.log.sql=true

# orm.xml for Supplier mapping
quarkus.hibernate-orm.mapping-files=META-INF/orm.xml

# REST Clients
quarkus.rest-client.product-reviews-api.url=http://localhost:8081
quarkus.rest-client.user-activity-api.url=http://localhost:8082

# Qute
quarkus.qute.property-not-found-strategy=throw-exception

# HTTP Port
quarkus.http.port=8080
```

### Product Reviews (application.properties)

```properties
# Application
quarkus.application.name=product-reviews

# MongoDB
quarkus.mongodb.connection-string=mongodb://localhost:27017
quarkus.mongodb.database=vintagestore_reviews

# HTTP Port
quarkus.http.port=8081
```

### User Activity (application.properties)

```properties
# Application
quarkus.application.name=user-activity

# MySQL Reactive DataSource
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=activity
quarkus.datasource.password=activity
quarkus.datasource.reactive.url=mysql://localhost:3306/vintagestore_activity

# Hibernate Reactive
quarkus.hibernate-orm.database.generation=update

# HTTP Port
quarkus.http.port=8082
```

---

## Development Setup

### Prerequisites

- JDK 17+
- Maven 3.8+
- Docker and Docker Compose
- IDE with Quarkus support

### Running with Dev Services

```bash
# Start VintageStore (PostgreSQL auto-provisioned)
cd vintagestore
mvn quarkus:dev

# Start Product Reviews (MongoDB auto-provisioned)
cd product-reviews
mvn quarkus:dev

# Start User Activity (MySQL auto-provisioned)
cd user-activity
mvn quarkus:dev
```

### Running with Docker Compose

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: vintagestore
      POSTGRES_USER: vintage
      POSTGRES_PASSWORD: vintage
    ports:
      - "5432:5432"

  mongodb:
    image: mongo:6
    ports:
      - "27017:27017"

  mysql:
    image: mysql:8
    environment:
      MYSQL_DATABASE: vintagestore_activity
      MYSQL_USER: activity
      MYSQL_PASSWORD: activity
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"
```

---

## Testing Strategy

| Module          | Test Type   | Technology                              |
|-----------------|-------------|-----------------------------------------|
| VintageStore    | Integration | @QuarkusTest, Dev Services (PostgreSQL) |
| VintageStore    | Unit        | PanacheMock, Mockito                    |
| VintageStore    | REST Client | @QuarkusTest with WireMock              |
| Supplier        | Unit        | JUnit 5                                 |
| Product Reviews | Integration | @QuarkusTest, Dev Services (MongoDB)    |
| User Activity   | Integration | @QuarkusTest, Dev Services (MySQL)      |
| All             | REST API    | RestAssured                             |
| All             | End-to-End  | Playwright / Selenium                   |

---

## Project Structure

```
parent/
├── pom.xml (parent POM)
├── supplier/
│   ├── pom.xml
│   └── src/main/java/
│       └── com/pluralsight/persistence/supplier/
│           └── Supplier.java (Plain POJO)
├── catalog/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/pluralsight/persistence
│       │   │       ├── catalog/
│       │   │       │   ├── model/
│       │   │       │   ├── repository/
│       │   │       │   └── resource/
│       │   │       ├── supplier/
│       │   │       │   ├── repository/ (SupplierRepository)
│       │   │       │   └── resource/ (SupplierResource)
│       │   │       ├── review/
│       │   │       │   ├── client/ (ProductReviewClient)
│       │   │       │   ├── dto/ (ProductReviewDTO)
│       │   │       │   └── resource/ (ReviewFacadeResource)
│       │   │       ├── activity/
│       │   │       │   ├── client/ (UserActivityClient)
│       │   │       │   ├── dto/ (UserActivityDTO)
│       │   │       │   └── resource/ (ActivityFacadeResource)
│       │   │       └── web/ (Renarde controllers)
│       │   └── resources/
│       │       ├── META-INF/
│       │       │   └── orm.xml
│       │       ├── templates/ (Qute templates)
│       │       └── application.properties
│       └── test/
├── reviews/
│   ├── pom.xml
│   └── src/
│       ├── main/java/
│       │   └── com/pluralsight/persistence/reviews/
│       │       ├── model/ (ProductReview)
│       │       ├── repository/ (ProductReviewRepository)
│       │       └── resource/ (ProductReviewResource)
│       └── test/
└── activity/
    ├── pom.xml
    └── src/
        ├── main/java/
        │   └── com/pluralsight/persistence/activity/
        │       ├── model/ (UserActivityLog, ActivityAction)
        │       ├── repository/ (UserActivityLogRepository)
        │       └── resource/ (UserActivityLogResource)
        └── test/
```