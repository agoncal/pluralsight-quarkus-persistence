package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Book;
import com.pluralsight.persistence.catalog.model.Language;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.Hibernate;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

  private static final Logger LOG = Logger.getLogger(BookResource.class);

  @GET
  public List<Book> getAllBooks(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("10") int size,
      @QueryParam("inStock") Boolean inStock,
      @QueryParam("language") Language language,
      @QueryParam("publisher") Long publisherId,
      @QueryParam("sortBy") @DefaultValue("title") String sortBy,
      @QueryParam("sortDir") @DefaultValue("asc") String sortDir) {
    LOG.info("Entering getAllBooks() with page=" + page + ", size=" + size + ", inStock=" + inStock + ", language=" + language + ", publisher=" + publisherId + ", sortBy=" + sortBy + ", sortDir=" + sortDir);

    // Build sort
    Sort sort = Sort.by(sortBy);
    if ("desc".equalsIgnoreCase(sortDir)) {
      sort = sort.descending();
    }

    // Build query dynamically
    StringBuilder query = new StringBuilder();
    Map<String, Object> params = new HashMap<>();
    List<String> conditions = new ArrayList<>();

    if (inStock != null) {
      if (inStock) {
        conditions.add("stock > 0");
      } else {
        conditions.add("stock = 0");
      }
    }

    if (language != null) {
      conditions.add("language = :language");
      params.put("language", language);
    }

    if (publisherId != null) {
      conditions.add("publisher.id = :publisherId");
      params.put("publisherId", publisherId);
    }

    if (conditions.isEmpty()) {
      return Book.findAll(sort).page(Page.of(page, size)).list();
    }

    query.append(String.join(" and ", conditions));
    return Book.find(query.toString(), sort, params).page(Page.of(page, size)).list();
  }

  @GET
  @Path("/{id}")
  @Transactional
  public Response getBook(@PathParam("id") Long id) {
    LOG.info("Entering getBook() with id: " + id);
    Book book = Book.findById(id);
    if (book == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    Hibernate.initialize(book.publisher);
    Hibernate.initialize(book.authors);
    return Response.ok(book).build();
  }

  @POST
  @Transactional
  public Response createBook(@Valid Book book) {
    LOG.info("Entering createBook() with title: " + book.title);
    book.persist();
    return Response.created(URI.create("/api/books/" + book.id)).entity(book).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateBook(@PathParam("id") Long id, @Valid Book book) {
    LOG.info("Entering updateBook() with id: " + id);
    Book existingBook = Book.findById(id);
    if (existingBook == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingBook.title = book.title;
    existingBook.description = book.description;
    existingBook.price = book.price;
    existingBook.stock = book.stock;
    existingBook.isbn = book.isbn;
    existingBook.nbOfPages = book.nbOfPages;
    existingBook.publicationDate = book.publicationDate;
    existingBook.language = book.language;
    existingBook.publisher = book.publisher;
    Hibernate.initialize(existingBook.publisher);
    Hibernate.initialize(existingBook.authors);
    return Response.ok(existingBook).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteBook(@PathParam("id") Long id) {
    LOG.info("Entering deleteBook() with id: " + id);
    Book book = Book.findById(id);
    if (book == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    book.delete();
    return Response.noContent().build();
  }
}
