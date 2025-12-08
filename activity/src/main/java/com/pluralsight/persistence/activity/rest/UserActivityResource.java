package com.pluralsight.persistence.activity.rest;

import com.pluralsight.persistence.activity.model.Action;
import com.pluralsight.persistence.activity.model.UserActivityLog;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
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

import java.net.URI;
import java.util.List;

@Path("/api/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserActivityResource {

  @GET
  public Uni<List<UserActivityLog>> getAll() {
    return UserActivityLog.listAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Response> getById(@PathParam("id") Long id) {
    return UserActivityLog.<UserActivityLog>findById(id)
      .onItem().transform(activity -> {
        if (activity == null) {
          return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(activity).build();
      });
  }

  @POST
  @WithTransaction
  public Uni<Response> create(@Valid UserActivityLog activity) {
    return activity.<UserActivityLog>persist()
      .onItem().transform(persisted ->
        Response.created(URI.create("/api/activities/" + persisted.id))
          .entity(persisted)
          .build());
  }

  @PUT
  @Path("/{id}")
  @WithTransaction
  public Uni<Response> update(@PathParam("id") Long id, @Valid UserActivityLog activity) {
    return UserActivityLog.<UserActivityLog>findById(id)
      .onItem().transformToUni(existing -> {
        if (existing == null) {
          return Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND).build());
        }
        existing.username = activity.username;
        existing.action = activity.action;
        existing.item = activity.item;
        existing.searchQuery = activity.searchQuery;
        existing.ipAddress = activity.ipAddress;
        existing.userAgent = activity.userAgent;
        existing.timestamp = activity.timestamp;
        return existing.<UserActivityLog>persist()
          .onItem().transform(updated -> Response.ok(updated).build());
      });
  }

  @DELETE
  @Path("/{id}")
  @WithTransaction
  public Uni<Response> delete(@PathParam("id") Long id) {
    return UserActivityLog.<UserActivityLog>findById(id)
      .onItem().transformToUni(activity -> {
        if (activity == null) {
          return Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND).build());
        }
        return activity.delete()
          .onItem().transform(ignored -> Response.noContent().build());
      });
  }

  @GET
  @Path("/user/{username}")
  public Uni<List<UserActivityLog>> getByUsername(@PathParam("username") String username) {
    return UserActivityLog.list("username", username);
  }

  @GET
  @Path("/action/{action}")
  public Uni<List<UserActivityLog>> getByAction(@PathParam("action") Action action) {
    return UserActivityLog.list("action", action);
  }
}
