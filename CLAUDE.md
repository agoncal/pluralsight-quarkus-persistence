# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

VintageStore is a Quarkus persistence demonstration project for a Pluralsight course. It's a multi-module Maven project showcasing various data persistence
patterns including Hibernate ORM with Panache, Reactive Hibernate, and MongoDB.

## Build Commands

```bash
# Build all modules
mvn clean package

# Run tests
mvn clean test

# Run integration tests
mvn clean verify

# Development mode with live reload (from module directory)
cd catalog && mvn quarkus:dev

# Build native executable
mvn package -Dnative -Dquarkus.native.container-build=true
```

## Project Structure

```
├── catalog/     # Main web app (PostgreSQL, Renarde/Qute, REST)
├── activity/    # Microservice (Reactive MySQL)
├── reviews/     # Microservice (MongoDB)
├── supplier/    # Legacy ORM mapping (embedded in catalog)
└── dummy/       # Dummy directory (do not read)
```

## Architecture

- **Java 21**, **Quarkus 3.30.2**, **Maven multi-module**
- **Catalog** (port 8080): Main facade with REST API and Qute/Renarde web UI, communicates with microservices via REST client
- **Activity**: Reactive persistence with Hibernate Reactive + MySQL
- **Reviews**: Document storage with MongoDB Panache
- **Supplier**: Legacy XML-based ORM mapping example

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

## Configuration

- Main config: `[module]/src/main/resources/application.properties`
- Dev UI: http://localhost:8080/q/dev/ (in dev mode)
- Formatting: 2-space indent, UTF-8, LF endings (see `.editorconfig`)

## Testing

- Unit tests: `*Test.java`
- Integration tests: `*IT.java`
- Frameworks: JUnit 5, REST Assured, Mockito

## Important Files

- `specifications.md` - Complete domain and API specifications
