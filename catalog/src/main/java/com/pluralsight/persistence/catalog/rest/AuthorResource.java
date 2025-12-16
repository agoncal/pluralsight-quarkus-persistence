package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Author;
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
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;

@Path("/api/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

  private static final Logger LOG = Logger.getLogger(AuthorResource.class);

  @GET
  public List<Author> getAllAuthors() {
    LOG.info("Entering getAllAuthors()");
    return Author.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getAuthor(@PathParam("id") Long id) {
    LOG.info("Entering getAuthor() with id: " + id);
    Author author = Author.findById(id);
    if (author == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(author).build();
  }

  @POST
  @Transactional
  public Response createAuthor(@Valid Author author) {
    LOG.info("Entering createAuthor() with name: " + author.firstName + " " + author.lastName);
    author.persist();
    return Response.created(URI.create("/api/authors/" + author.id)).entity(author).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateAuthor(@PathParam("id") Long id, @Valid Author author) {
    LOG.info("Entering updateAuthor() with id: " + id);
    Author existingAuthor = Author.findById(id);
    if (existingAuthor == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingAuthor.firstName = author.firstName;
    existingAuthor.lastName = author.lastName;
    existingAuthor.bio = author.bio;
    existingAuthor.dateOfBirth = author.dateOfBirth;
    existingAuthor.preferredLanguage = author.preferredLanguage;
    existingAuthor.website = author.website;
    return Response.ok(existingAuthor).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteAuthor(@PathParam("id") Long id) {
    LOG.info("Entering deleteAuthor() with id: " + id);
    Author author = Author.findById(id);
    if (author == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    author.delete();
    return Response.noContent().build();
  }
}
