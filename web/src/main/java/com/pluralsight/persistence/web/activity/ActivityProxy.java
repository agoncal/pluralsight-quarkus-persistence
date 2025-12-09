package com.pluralsight.persistence.web.activity;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "activities")
@Path("/api/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ActivityProxy {

  @GET
  List<UserActivityLogDTO> getAll();

  @GET
  @Path("/user/{username}")
  List<UserActivityLogDTO> getByUsername(@PathParam("username") String username);

  @GET
  @Path("/action/{action}")
  List<UserActivityLogDTO> getByAction(@PathParam("action") Action action);
}