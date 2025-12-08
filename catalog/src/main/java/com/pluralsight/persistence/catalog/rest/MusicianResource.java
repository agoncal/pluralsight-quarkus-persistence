package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.Musician;
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

@Path("/api/musicians")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MusicianResource {

  @GET
  public List<Musician> getAllMusicians() {
    return Musician.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getMusician(@PathParam("id") Long id) {
    Musician musician = Musician.findById(id);
    if (musician == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(musician).build();
  }

  @POST
  @Transactional
  public Response createMusician(@Valid Musician musician) {
    musician.persist();
    return Response.created(URI.create("/api/musicians/" + musician.id)).entity(musician).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateMusician(@PathParam("id") Long id, @Valid Musician musician) {
    Musician existingMusician = Musician.findById(id);
    if (existingMusician == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingMusician.firstName = musician.firstName;
    existingMusician.lastName = musician.lastName;
    existingMusician.bio = musician.bio;
    existingMusician.dateOfBirth = musician.dateOfBirth;
    existingMusician.stageName = musician.stageName;
    existingMusician.instrument = musician.instrument;
    return Response.ok(existingMusician).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteMusician(@PathParam("id") Long id) {
    Musician musician = Musician.findById(id);
    if (musician == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    musician.delete();
    return Response.noContent().build();
  }
}
