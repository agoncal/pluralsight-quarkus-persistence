package com.pluralsight.persistence.web.activity;

import io.smallrye.mutiny.Uni;
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
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "activities")
@Path("/api/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ActivityProxy {

  @GET
  public Uni<List<UserActivityLogDTO>> getAll();

  @GET
  @Path("/{id}")
  public Uni<Response> getById(@PathParam("id") Long id);

  @POST
  public Uni<Response> create(@Valid UserActivityLogDTO activity);

  @PUT
  @Path("/{id}")
  public Uni<Response> update(@PathParam("id") Long id, @Valid UserActivityLogDTO activity);

  @DELETE
  @Path("/{id}")
  public Uni<Response> delete(@PathParam("id") Long id);

  @GET
  @Path("/user/{username}")
  public Uni<List<UserActivityLogDTO>> getByUsername(@PathParam("username") String username);

  @GET
  @Path("/action/{action}")
  public Uni<List<UserActivityLogDTO>> getByAction(@PathParam("action") Action action);
}