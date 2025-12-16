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
│                           WEB APPLICATION (:8080)                           │
│                         (Quarkus + Qute + Renarde)                          │
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                    MICROPROFILE REST CLIENTS                        │    │
│  │  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐      │    │
│  │  │  CatalogProxy   │  │  ReviewsProxy   │  │  ActivityProxy  │      │    │
│  │  └─────────────────┘  └─────────────────┘  └─────────────────┘      │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                         WEB PAGES (Qute + Renarde)                  │    │
│  │  Exposes Web Pages for all domains                                  │    │
│  │  Aggregates data from remote microservices                          │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
                                     │
        ┌────────────────────────────┼────────────────────────────┐
        │                            │                            │
        ▼                            ▼                            ▼
┌─────────────────────┐  ┌─────────────────────┐  ┌─────────────────────┐
│  CATALOG (:8083)    │  │  REVIEWS (:8084)    │  │  ACTIVITY (:8082)   │
│   (Microservice)    │  │   (Microservice)    │  │   (Microservice)    │
│  ┌───────────────┐  │  │  ┌───────────────┐  │  │  ┌───────────────┐  │
│  │  Book, CD     │  │  │  │ ProductReview │  │  │  │UserActivityLog│  │
│  │  Author       │  │  │  │               │  │  │  │  (Hibernate   │  │
│  │  Musician     │  │  │  (persistence.xml)  │  │  │   Reactive)   │  │
│  │  Publisher    │  │  │  └───────────────┘  │  │  └───────────────┘  │
│  │  Track        │  │  │                     │  │                     │
│  └───────────────┘  │  │      MariaDB        │  │       MySQL         │
│                     │  │                     │  │                     │
│     PostgreSQL      │  └─────────────────────┘  └─────────────────────┘
└─────────────────────┘
           │
           ▼
┌─────────────────────┐
│   CUSTOMER MODULE   │
│    (JAR Library)    │
│  ┌───────────────┐  │
│  │   Customer    │  │
│  └───────────────┘  │
│   No Database       │
│   (embedded JAR)    │
└─────────────────────┘
```

## Modules

### 1. Web Application (Port 8080)

The main web application serving as the storefront. It uses MicroProfile REST Client to communicate with the microservices and renders web pages using Qute and
Renarde.

#### Technical Stack

| Technology               | Purpose                                    |
|--------------------------|--------------------------------------------|
| Quarkus                  | Application framework                      |
| Qute                     | Templating engine for HTML pages           |
| Renarde                  | Web framework for server-side rendering    |
| MicroProfile REST Client | Communication with microservices           |
| RESTEasy Reactive        | REST client for microservice communication |

#### Features

- Product catalog browsing (books and CDs) via Catalog microservice
- Publisher and artist information pages via Catalog microservice
- Product reviews display and submission via Reviews microservice
- User activity tracking and display via Activity microservice
- Search, filtering, sorting, and pagination

#### REST Clients

**CatalogProxy** - Communicates with Catalog microservice (port 8083)
**ReviewsProxy** - Communicates with Reviews microservice (port 8084)
**ActivityProxy** - Communicates with Activity microservice (port 8082)

#### Web Pages (Qute + Renarde)

##### Public Pages

| Page         | Path        | Description                         |
|--------------|-------------|-------------------------------------|
| Home         | /           | Landing page with featured items    |
| Book List    | /books      | Browsable book catalog with filters |
| Book Details | /books/{id} | Single book page with reviews       |
| CD List      | /cds        | Browsable CD catalog with filters   |
| CD Details   | /cds/{id}   | Single CD page with reviews         |

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
| Manage Authors    | /admin/authors    | CRUD for authors         |
| Manage Books      | /admin/books      | CRUD for books           |
| Manage CDs        | /admin/cds        | CRUD for CDs             |
| Manage Customers  | /admin/customers  | Customer management      |
| Manage Musicians  | /admin/musicians  | CRUD for musicians       |
| Manage Publishers | /admin/publishers | CRUD for publishers      |
| Manage Orders     | /admin/orders     | Order management         |
| Manage Users      | /admin/users      | User management          |
| View Activities   | /admin/activities | User activity monitoring |
| View Reviews      | /admin/reviews    | Review display           |

---

### 2. Catalog Microservice (Port 8083)

A dedicated microservice for managing the product catalog including books, CDs, authors, musicians, and publishers.

#### Technical Stack

| Technology                 | Purpose                      |
|----------------------------|------------------------------|
| Quarkus                    | Application framework        |
| Hibernate ORM with Panache | Data persistence             |
| PostgreSQL                 | Relational database          |
| Bean Validation            | Entity constraint validation |
| RESTEasy Reactive          | REST API exposure            |

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

##### Enumerations

| Enum        | Values                                                                        |
|-------------|-------------------------------------------------------------------------------|
| Language    | ENGLISH, FRENCH, SPANISH, GERMAN, PORTUGUESE, ITALIAN, JAPANESE, CHINESE      |
| MusicGenre  | ROCK, POP, JAZZ, CLASSICAL, ELECTRONIC, HIP_HOP, COUNTRY, BLUES, METAL, OTHER |
| UserRole    | CUSTOMER, ADMIN, MANAGER                                                      |
| OrderStatus | PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED                             |

#### REST Endpoints

| Method | Path                 | Description                    |
|--------|----------------------|--------------------------------|
| GET    | /api/authors         | List all authors               |
| GET    | /api/authors/{id}    | Get author details             |
| POST   | /api/authors         | Create a new author            |
| PUT    | /api/authors/{id}    | Update an author               |
| DELETE | /api/authors/{id}    | Delete an author               |
| GET    | /api/books           | List books (page, size params) |
| GET    | /api/books/{id}      | Get book details               |
| POST   | /api/books           | Create a new book              |
| PUT    | /api/books/{id}      | Update a book                  |
| DELETE | /api/books/{id}      | Delete a book                  |
| GET    | /api/cds             | List CDs (page, size params)   |
| GET    | /api/cds/{id}        | Get CD details                 |
| POST   | /api/cds             | Create a new CD                |
| PUT    | /api/cds/{id}        | Update a CD                    |
| DELETE | /api/cds/{id}        | Delete a CD                    |
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

REST endpoints managing customers, orders, and suppliers are handled via the Customer and Supplier modules:

| Method | Path                 | Description                 |
|--------|----------------------|-----------------------------|
| GET    | /api/users           | List all users              |
| GET    | /api/users/{id}      | Get user details            |
| POST   | /api/users           | Create a new user           |
| PUT    | /api/users/{id}      | Update a user               |
| DELETE | /api/users/{id}      | Delete a user               |
| GET    | /api/customers       | List all customers          |
| GET    | /api/customers/{id}  | Get customer details        |
| POST   | /api/customers       | Create a new customer       |
| PUT    | /api/customers/{id}  | Update a customer           |
| DELETE | /api/customers/{id}  | Delete a customer           |
| GET    | /api/pos             | List all purchase orders    |
| GET    | /api/pos/{id}        | Get purchase order details  |
| POST   | /api/pos             | Create a new purchase order |
| PUT    | /api/pos/{id}        | Update a purchase order     |
| DELETE | /api/pos/{id}        | Delete a purchase order     |
| GET    | /api/suppliers       | List all suppliers          |
| GET    | /api/suppliers/{id}  | Get supplier details        |
| POST   | /api/suppliers       | Create a new supplier       |
| PUT    | /api/suppliers/{id}  | Update a supplier           |
| DELETE | /api/suppliers/{id}  | Delete a supplier           |

---

### 3. Customer Module (JAR Library)

A standalone JAR file containing legacy POJO entities without JPA annotations. This module demonstrates integration with external legacy code using orm.xml
mapping.

#### Technical Stack

| Technology         | Purpose              |
|--------------------|----------------------|
| Plain Java         | POJO definitions     |
| No JPA annotations | Legacy compatibility |

#### Entities

**Customer** (JPA Entity - mapped via peristence.xml in VintageStore)

#### Integration with VintageStore

The VintageStore application includes this JAR as a dependency and uses:

- **persistence.xml** to map the Customers as JPA entities
- **PanacheRepository** to provide data access methods

---

### 4. Supplier Module (JAR Library)

A standalone JAR file containing legacy POJO entities without JPA annotations, similar to the Customer module. This module demonstrates integration with external legacy code using orm.xml mapping.

#### Technical Stack

| Technology         | Purpose              |
|--------------------|----------------------|
| Plain Java         | POJO definitions     |
| No JPA annotations | Legacy compatibility |

#### Entities

**Supplier** (JPA Entity - mapped via orm.xml in Catalog)

| Attribute    | Type    | Constraints    |
|--------------|---------|----------------|
| id           | Long    | Auto-generated |
| companyName  | String  |                |
| contactName  | String  |                |
| contactEmail | String  |                |
| country      | String  |                |
| createdDate  | Instant | Auto-set       |

#### Integration with Catalog Microservice

The Catalog microservice includes this JAR as a dependency and uses:

- **orm.xml** to map the Supplier as a JPA entity
- **PanacheRepository** to provide data access methods

#### REST Endpoints (exposed via Catalog Microservice)

| Method | Path                 | Description            |
|--------|----------------------|------------------------|
| GET    | /api/suppliers       | List all suppliers     |
| GET    | /api/suppliers/{id}  | Get supplier details   |
| POST   | /api/suppliers       | Create a new supplier  |
| PUT    | /api/suppliers/{id}  | Update a supplier      |
| DELETE | /api/suppliers/{id}  | Delete a supplier      |

---

### 5. Product Reviews Microservice (Port 8084)

A dedicated microservice for managing product reviews using MariaDB with legacy JPA persistence.

#### Technical Stack

| Technology            | Purpose               |
|-----------------------|-----------------------|
| Quarkus               | Application framework |
| Hibernate ORM Panache | Data persistence      |
| MariaDB               | Relational database   |
| RESTEasy Reactive     | REST API exposure     |
| Jackson               | JSON serialization    |

#### Features

- Create, read, update, delete product reviews
- Query reviews by item type and item ID

#### Entities

**ProductReview** (Panache MariaDB Entity)

| Attribute   | Type     | Constraints                 |
|-------------|----------|-----------------------------|
| id          | Long     | Auto-generated              |
| itemId      | Long     | Reference to Item, not null |
| itemType    | ItemType | Enum, not null              |
| username    | String   | Max 50, not null            |
| rating      | Integer  | 0-5, not null               |
| title       | String   | Max 100, not null           |
| comment     | String   | Max 1024                    |
| createdDate | Instant  | Auto-set                    |

#### Enumerations

| Enum     | Values   |
|----------|----------|
| ItemType | BOOK, CD |

#### REST Endpoints

| Method | Path                               | Description        |
|--------|------------------------------------|--------------------|
| GET    | /api/reviews                       | List all reviews   |
| GET    | /api/reviews/{id}                  | Get review by ID   |
| GET    | /api/reviews/item/{itemType}/{itemId} | Get reviews for an item |
| POST   | /api/reviews                       | Create a new review |
| PUT    | /api/reviews/{id}                  | Update a review    |
| DELETE | /api/reviews/{id}                  | Delete a review    |

#### Sample JSON Response

```json
{
  "id": 1,
  "itemId": 42,
  "itemType": "BOOK",
  "username": "johndoe",
  "rating": 5,
  "title": "Excellent book!",
  "comment": "This is one of the best books I've ever read on the subject.",
  "createdDate": "2024-01-15T10:30:00Z"
}
```

---

### 6. User Activity Microservice (Port 8082)

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

| Attribute   | Type    | Constraints                        |
|-------------|---------|------------------------------------|
| id          | Long    | Auto-generated                     |
| username    | String  | Max 50, not null                   |
| action      | Action  | Enum, not null                     |
| item        | String  | Not null, item identifier          |
| searchQuery | String  | Max 500, optional                  |
| ipAddress   | String  | Max 45 (IPv6 support), not null    |
| userAgent   | String  | Max 500, not null                  |
| timestamp   | Instant | Not null, auto-set                 |

#### Enumeration

| Enum   | Values                                                                                                     |
|--------|------------------------------------------------------------------------------------------------------------|
| Action | VIEWED_ITEM, ADDED_TO_CART, REMOVED_FROM_CART, SEARCHED, LOGGED_IN, LOGGED_OUT, PLACED_ORDER, WROTE_REVIEW |

#### REST Endpoints

All endpoints return reactive types (Uni).

| Method | Path                              | Description                   | Return Type                   |
|--------|-----------------------------------|-------------------------------|-------------------------------|
| GET    | /api/activities                   | List all activities           | Uni\<List\<UserActivityLog\>\> |
| GET    | /api/activities/{id}              | Get activity by ID            | Uni\<Response\>               |
| GET    | /api/activities/user/{username}   | Get activities for a user     | Uni\<List\<UserActivityLog\>\> |
| GET    | /api/activities/action/{action}   | Get activities by action type | Uni\<List\<UserActivityLog\>\> |
| POST   | /api/activities                   | Record a new activity         | Uni\<Response\>               |
| PUT    | /api/activities/{id}              | Update an activity            | Uni\<Response\>               |
| DELETE | /api/activities/{id}              | Delete an activity            | Uni\<Response\>               |

#### Sample JSON Response

```json
{
  "id": 12345,
  "username": "johndoe",
  "action": "VIEWED_ITEM",
  "item": "BOOK-101",
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
│ company_name    │                             │ item            │
│ contact_name    │                             │ order_fk (FK)   │
│ contact_email   │                             └─────────────────┘
│ country         │
│ created_date    │
└─────────────────┘
```

### MariaDB (Product Reviews)

```
┌─────────────────────┐
│     t_reviews       │
├─────────────────────┤
│ id (PK)             │
│ item_id             │
│ item_type           │
│ username            │
│ rating              │
│ title               │
│ comment             │
│ created_date        │
└─────────────────────┘

Indexes:
- item_id, item_type (for product lookup)
- username (for user reviews)
- created_date (for sorting)
```

### MySQL (User Activity)

```
┌─────────────────────┐
│ t_user_activity_log │
├─────────────────────┤
│ id (PK)             │
│ username            │
│ action              │
│ item                │
│ search_query        │
│ ip_address          │
│ user_agent          │
│ timestamp           │
└─────────────────────┘

Indexes:
- username (for user activity lookup)
- action (for action type filtering)
- timestamp (for time-based queries)
```

---

## Configuration

### Web Application (application.properties)

```properties
# Application
quarkus.application.name=Vintage Store
quarkus.http.port=8080
# REST Clients
quarkus.rest-client.catalog-api.url=http://localhost:8083
quarkus.rest-client.reviews-api.url=http://localhost:8084
quarkus.rest-client.activity-api.url=http://localhost:8082
# Qute
quarkus.qute.property-not-found-strategy=throw-exception
```

### Catalog Microservice (application.properties)

```properties
# Application
quarkus.application.name=Catalog
quarkus.http.port=8083
# PostgreSQL DataSource
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=catalog
quarkus.datasource.password=catalog
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/catalog
# Hibernate ORM
quarkus.hibernate-orm.database.generation=update
quarkus.hibernate-orm.log.sql=true
```

### Product Reviews (application.properties)

```properties
# Application
quarkus.application.name=Product Reviews
quarkus.http.port=8084
# MariaDB
quarkus.mariadb.connection-string=mariadb://localhost:27017
quarkus.mariadb.database=vintagestore_reviews
```

### User Activity (application.properties)

```properties
# Application
quarkus.application.name=User Activity
quarkus.http.port=8082
# MySQL Reactive DataSource
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=activity
quarkus.datasource.password=activity
quarkus.datasource.reactive.url=mysql://localhost:3306/vintagestore_activity
# Hibernate Reactive
quarkus.hibernate-orm.database.generation=update
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
# Start Web Application (port 8080)
cd web
mvn quarkus:dev

# Start Catalog Microservice (PostgreSQL auto-provisioned, port 8083)
cd catalog
mvn quarkus:dev

# Start Product Reviews (MariaDB auto-provisioned, port 8084)
cd reviews
mvn quarkus:dev

# Start User Activity (MySQL auto-provisioned, port 8082)
cd activity
mvn quarkus:dev
```

---

## Testing Strategy

| Module          | Test Type   | Technology                              |
|-----------------|-------------|-----------------------------------------|
| Web             | Integration | @QuarkusTest with WireMock              |
| Web             | Unit        | Mockito                                 |
| Catalog         | Integration | @QuarkusTest, Dev Services (PostgreSQL) |
| Catalog         | Unit        | PanacheMock, Mockito                    |
| Customer        | Unit        | JUnit 5                                 |
| Product Reviews | Integration | @QuarkusTest, Dev Services (MariaDB)    |
| User Activity   | Integration | @QuarkusTest, Dev Services (MySQL)      |
| All             | REST API    | RestAssured                             |
| All             | End-to-End  | Playwright / Selenium                   |

### Catalog Module Test Classes

| Test Class              | Purpose                                           |
|-------------------------|---------------------------------------------------|
| CatalogTest             | Entity tests with @TestTransaction (auto-rollback)|
| CatalogRepositoryTest   | Mocking tests (PanacheMock + @InjectMock)         |
| CatalogResourceTest     | REST API integration tests (RestAssured)          |
| BookResourceTest        | Book REST endpoint tests                          |
| CDResourceTest          | CD REST endpoint tests                            |
| AuthorResourceTest      | Author REST endpoint tests                        |
| MusicianResourceTest    | Musician REST endpoint tests                      |
| PublisherResourceTest   | Publisher REST endpoint tests                     |
| UserResourceTest        | User REST endpoint tests                          |
| CustomerResourceTest    | Customer REST endpoint tests                      |
| PurchaseOrderResourceTest | Purchase order REST endpoint tests              |
| SupplierResourceTest    | Supplier REST endpoint tests                      |

### Pagination Parameters

Books and CDs endpoints support pagination via query parameters:

| Parameter | Type    | Default | Description               |
|-----------|---------|---------|---------------------------|
| page      | Integer | 0       | Page number (0-indexed)   |
| size      | Integer | 10      | Number of items per page  |

Example: `GET /api/books?page=0&size=10`

---

## Project Structure

```
parent/
├── pom.xml (parent POM)
├── web/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/pluralsight/persistence/web/
│       │   │       ├── WebApplication.java
│       │   │       ├── catalog/
│       │   │       │   └── CatalogProxy.java
│       │   │       ├── reviews/
│       │   │       │   └── ReviewsProxy.java
│       │   │       └── activity/
│       │   │           └── ActivityProxy.java
│       │   └── resources/
│       │       ├── templates/ (Qute templates)
│       │       └── application.properties
│       └── test/
├── catalog/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/pluralsight/persistence/catalog/
│       │   │       ├── model/
│       │   │       ├── repository/
│       │   │       └── resource/
│       │   └── resources/
│       │       └── application.properties
│       └── test/
├── customer/
│   ├── pom.xml
│   └── src/main/java/
│       └── com/pluralsight/persistence/customer/
│           └── Customer.java (Plain POJO)
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