package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.CD;
import io.quarkus.panache.common.Page;
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

import java.net.URI;
import java.util.List;

@Path("/api/cds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CDResource {

  @GET
  public List<CD> getAllCDs(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("10") int size) {
    return CD.findAll().page(Page.of(page, size)).list();
  }

  @GET
  @Path("/{id}")
  @Transactional
  public Response getCD(@PathParam("id") Long id) {
    CD cd = CD.findById(id);
    if (cd == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    Hibernate.initialize(cd.musicians);
    Hibernate.initialize(cd.tracks);
    return Response.ok(cd).build();
  }

  @POST
  @Transactional
  public Response createCD(@Valid CD cd) {
    cd.persist();
    return Response.created(URI.create("/api/cds/" + cd.id)).entity(cd).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateCD(@PathParam("id") Long id, @Valid CD cd) {
    CD existingCD = CD.findById(id);
    if (existingCD == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingCD.title = cd.title;
    existingCD.description = cd.description;
    existingCD.price = cd.price;
    existingCD.stock = cd.stock;
    existingCD.ean = cd.ean;
    existingCD.musicCompany = cd.musicCompany;
    existingCD.genre = cd.genre;
    existingCD.totalDuration = cd.totalDuration;
    existingCD.releaseDate = cd.releaseDate;
    Hibernate.initialize(existingCD.musicians);
    Hibernate.initialize(existingCD.tracks);
    return Response.ok(existingCD).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteCD(@PathParam("id") Long id) {
    CD cd = CD.findById(id);
    if (cd == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    cd.delete();
    return Response.noContent().build();
  }
}
