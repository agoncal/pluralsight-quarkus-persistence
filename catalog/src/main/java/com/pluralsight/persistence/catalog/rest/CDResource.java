package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.CD;
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

@Path("/api/cds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CDResource {

  @GET
  public List<CD> getAllCDs() {
    return CD.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getCD(@PathParam("id") Long id) {
    CD cd = CD.findById(id);
    if (cd == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
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
