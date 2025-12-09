package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Book;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.Hibernate;

import java.net.URI;
import java.util.List;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

  @GET
  public List<Book> getAllBooks() {
    return Book.listAll();
  }

  @GET
  @Path("/{id}")
  @Transactional
  public Response getBook(@PathParam("id") Long id) {
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
    book.persist();
    return Response.created(URI.create("/api/books/" + book.id)).entity(book).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateBook(@PathParam("id") Long id, @Valid Book book) {
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
    Book book = Book.findById(id);
    if (book == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    book.delete();
    return Response.noContent().build();
  }
}
