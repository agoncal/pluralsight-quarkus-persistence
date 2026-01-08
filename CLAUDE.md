# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

VintageStore is a Quarkus persistence demonstration project for a Pluralsight course. It's a multi-module Maven project showcasing various data persistence
patterns including Hibernate ORM with Panache and Reactive Hibernate.

## Build Commands

```bash
# Build all modules
mvn clean package

# Run tests
mvn clean test

# Run a single test class (from module directory)
cd catalog && mvn test -Dtest=BookResourceTest

# Run a single test method
cd catalog && mvn test -Dtest=BookResourceTest#shouldCreateBook

# Run integration tests
mvn clean verify

# Development mode with live reload (from module directory)
cd catalog && mvn quarkus:dev

# Start databases with Docker Compose
docker compose -p vintage-store-db -f docker-compose.yml up
```

## Project Structure

```
├── web/         # Web interface (Qute/Renarde, MicroProfile REST Client)
├── catalog/     # Catalog microservice (PostgreSQL, REST API)
├── activity/    # User activity microservice (Reactive MySQL)
├── reviews/     # Product reviews microservice (Legacy JPA with MariaDB)
├── supplier/    # Supplier microservice (PostgreSQL)
├── customer/    # Legacy ORM mapping (embedded JAR)
└── dummy/       # Dummy directory (do not read)
```

## Architecture

- **Java 21**, **Quarkus 3.30.6**, **Maven multi-module**
- **Web** (port 8080): Main web interface with Qute/Renarde, uses MicroProfile REST Client to communicate with microservices
- **Activity** (port 8082): Reactive persistence with Hibernate Reactive + MySQL
- **Catalog** (port 8083): Catalog microservice with REST API (Books, CDs, Authors, Musicians, Publishers)
- **Reviews** (port 8084): Legacy JPA persistence with MariaDB
- **Customer**: Legacy JPA entities (embedded JAR)

## Key Patterns

### Panache Patterns

- **Active Record**: Most entities extend `PanacheEntity` (Book, CD, Author, User)
- **Repository**: Some entities use `PanacheRepository` (Customer, Order)

### ORM Inheritance

- **Single Table**: Item → Book, CD
- **Joined Table**: Person → Author, Musician

### REST Endpoints

All API endpoints use `/api/` prefix: `/api/books`, `/api/customers`, `/api/orders`, etc.

### Web UI (Renarde)

- Controllers extend `Controller` class
- Templates in `src/main/resources/templates/ControllerName/methodName.html`
- Use `@CheckedTemplate` for compile-time template validation
- Do NOT use breadcrumbs in the UI

## Configuration

- Main config: `[module]/src/main/resources/application.properties`
- Dev UI: http://localhost:8080/q/dev/ (in dev mode)
- Formatting: 2-space indent, UTF-8, LF endings (see `.editorconfig`)

## Testing

- Unit tests: `*Test.java`
- Integration tests: `*IT.java`
- Frameworks: JUnit 5, REST Assured, Mockito

### Test Patterns

**Entity Tests with @TestTransaction**
```java
@QuarkusTest
class CatalogTest {
  @Test
  @TestTransaction  // Auto-rollback after test
  void shouldCreateAndFindBook() {
    Book book = new Book();
    book.title = "Test Book";
    book.persist();
    assertNotNull(book.id);
  }
}
```

**Mocking Active Record Pattern (PanacheMock)**
```java
@QuarkusTest
class CatalogRepositoryTest {
  @Test
  void shouldMockBookCount() {
    PanacheMock.mock(Book.class);
    Mockito.when(Book.count()).thenReturn(42L);
    assertEquals(42L, Book.count());
    PanacheMock.verify(Book.class, Mockito.times(1)).count();
  }
}
```

**Mocking Repository Pattern (@InjectMock)**
```java
@QuarkusTest
class CatalogRepositoryTest {
  @InjectMock
  UserRepository userRepository;

  @Test
  void shouldMockUserRepositoryFindById() {
    User mockUser = new User();
    mockUser.setId(1L);
    Mockito.when(userRepository.findById(1L)).thenReturn(mockUser);
    assertNotNull(userRepository.findById(1L));
  }
}
```

### Test Configuration

To avoid conflicts between test data and `import.sql`, create `src/test/resources/application.properties`:
```properties
quarkus.hibernate-orm.sql-load-script=no-file
```

### Test Dependencies

```xml
<dependency>
  <groupId>io.quarkus</groupId>
  <artifactId>quarkus-junit5-mockito</artifactId>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>io.quarkus</groupId>
  <artifactId>quarkus-panache-mock</artifactId>
  <scope>test</scope>
</dependency>
```

## Pagination, Filtering, and Sorting

REST endpoints for books and CDs support pagination, filtering, and sorting:

### Books
```
GET /api/books?page=0&size=10&inStock=true&language=ENGLISH&publisher=1&sortBy=title&sortDir=asc
```

| Parameter | Type    | Description                                      |
|-----------|---------|--------------------------------------------------|
| page      | int     | Page number (0-indexed, default: 0)              |
| size      | int     | Items per page (default: 10)                     |
| inStock   | Boolean | Filter by availability (true/false/null for all) |
| language  | String  | Filter by language enum (ENGLISH, FRENCH, etc.)  |
| publisher | Long    | Filter by publisher ID                           |
| sortBy    | String  | Sort field (title, price; default: title)        |
| sortDir   | String  | Sort direction (asc, desc; default: asc)         |

### CDs
```
GET /api/cds?page=0&size=10&inStock=true&genre=ROCK&label=Records&sortBy=price&sortDir=desc
```

| Parameter | Type    | Description                                      |
|-----------|---------|--------------------------------------------------|
| page      | int     | Page number (0-indexed, default: 0)              |
| size      | int     | Items per page (default: 10)                     |
| inStock   | Boolean | Filter by availability (true/false/null for all) |
| genre     | String  | Filter by genre enum (ROCK, POP, JAZZ, etc.)     |
| label     | String  | Filter by music company (partial match)          |
| sortBy    | String  | Sort field (title, price; default: title)        |
| sortDir   | String  | Sort direction (asc, desc; default: asc)         |

### Implementation
Uses Panache dynamic queries with sorting:
```java
Sort sort = Sort.by(sortBy);
if ("desc".equalsIgnoreCase(sortDir)) {
  sort = sort.descending();
}
Book.find(query, sort, params).page(Page.of(page, size)).list();
```

## Caching

The catalog module uses Quarkus Cache (`quarkus-cache`) for optimizing frequently accessed endpoints.

### Cached Endpoints

| Endpoint         | Cache Name   | Cache Key |
|------------------|--------------|-----------|
| GET /api/books/{id} | book-cache | id        |
| GET /api/cds/{id}   | cd-cache   | id        |

### Implementation

```java
@GET
@Path("/{id}")
@CacheResult(cacheName = "book-cache")
public Response getBook(@CacheKey @PathParam("id") Long id) {
  // ...
}
```

### Cache Annotations

- `@CacheResult(cacheName = "...")` - Caches the method result
- `@CacheKey` - Marks the parameter used as cache key

## Performance Optimization

### Hibernate Configuration

```properties
# Enable statistics in dev mode to monitor N+1 issues
%dev.quarkus.hibernate-orm.statistics=true
%dev.quarkus.hibernate-orm.log.queries-slower-than-ms=100

# Batch fetching to reduce N+1 queries
quarkus.hibernate-orm.fetch.batch-size=16
quarkus.hibernate-orm.fetch.max-depth=3
```

### Fetch Joins for N+1 Prevention

Use `@NamedQuery` with `LEFT JOIN FETCH` to eagerly load relationships:

```java
@Entity
@NamedQuery(name = "Book.findByIdWithRelations",
  query = "SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.publisher LEFT JOIN FETCH b.authors WHERE b.id = :id")
public class Book extends Item {

  public static Optional<Book> findByIdWithRelations(Long id) {
    return find("#Book.findByIdWithRelations", Map.of("id", id)).firstResultOptional();
  }
}
```

### MultipleBagFetchException

When an entity has multiple `List` collections, Hibernate cannot fetch them all in one query. Solutions:

1. **Fetch one collection, batch-load the other:**
```java
@NamedQuery(name = "CD.findByIdWithMusicians",
  query = "SELECT DISTINCT c FROM CD c LEFT JOIN FETCH c.musicians WHERE c.id = :id")
public class CD extends Item {
  public static Optional<CD> findByIdWithRelations(Long id) {
    Optional<CD> cd = find("#CD.findByIdWithMusicians", Map.of("id", id)).firstResultOptional();
    cd.ifPresent(c -> c.tracks.size()); // Initialize via batch fetch
    return cd;
  }
}
```

2. **Use `Set` instead of `List`** for collections where order doesn't matter

### Hibernate Second-Level Cache

Entities can be cached at the Hibernate level using `@Cacheable`:

```java
@Entity
@Cacheable
public class Publisher extends PanacheEntity { }
```

For XML-mapped entities (orm.xml):
```xml
<entity class="com.pluralsight.persistence.supplier.model.Supplier" cacheable="true">
```

Configuration in `application.properties`:
```properties
quarkus.hibernate-orm.cache."com.pluralsight.persistence.catalog.model.Publisher".expiration.max-idle=1H
quarkus.hibernate-orm.cache."com.pluralsight.persistence.catalog.model.Publisher".memory.object-count=100
```

### Cached Entities

| Entity | Cache Duration | Max Objects |
|--------|----------------|-------------|
| Publisher | 1 hour | 100 |
| Supplier | 1 hour | 100 |

### Query Optimization Patterns

| Pattern | Use Case | Example |
|---------|----------|---------|
| Fetch Join | Single entity with relationships | `LEFT JOIN FETCH b.publisher` |
| Batch Fetching | Multiple entities with relationships | `fetch.batch-size=16` |
| Named Query | Reusable optimized queries | `@NamedQuery` |
| Entity Cache | Frequently accessed reference data | `@Cacheable` |
| Statistics | Detecting N+1 issues in dev | `statistics=true` |

## Important Files

- `specifications.md` - Complete domain and API specifications
