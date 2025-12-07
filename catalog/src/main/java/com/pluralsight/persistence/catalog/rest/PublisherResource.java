package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Publisher;
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

import java.net.URI;
import java.util.List;

@Path("/api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

  @GET
  public List<Publisher> getAllPublishers() {
    return Publisher.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getPublisher(@PathParam("id") Long id) {
    Publisher publisher = Publisher.findById(id);
    if (publisher == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(publisher).build();
  }

  @POST
  @Transactional
  public Response createPublisher(@Valid Publisher publisher) {
    publisher.persist();
    return Response.created(URI.create("/api/publishers/" + publisher.id)).entity(publisher).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updatePublisher(@PathParam("id") Long id, @Valid Publisher publisher) {
    Publisher existingPublisher = Publisher.findById(id);
    if (existingPublisher == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingPublisher.name = publisher.name;
    existingPublisher.country = publisher.country;
    existingPublisher.website = publisher.website;
    existingPublisher.foundedYear = publisher.foundedYear;
    return Response.ok(existingPublisher).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deletePublisher(@PathParam("id") Long id) {
    Publisher publisher = Publisher.findById(id);
    if (publisher == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    publisher.delete();
    return Response.noContent().build();
  }
}
