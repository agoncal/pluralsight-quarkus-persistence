package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.repository.UserRepository;
import com.pluralsight.persistence.customer.model.User;
import jakarta.inject.Inject;
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

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private static final Logger LOG = Logger.getLogger(UserResource.class);

  @Inject
  UserRepository userRepository;

  @GET
  public List<User> getAllUsers() {
    LOG.info("Entering getAllUsers()");
    return userRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getUser(@PathParam("id") Long id) {
    LOG.info("Entering getUser() with id: " + id);
    User user = userRepository.findById(id);
    if (user == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(user).build();
  }

  @GET
  @Path("/username/{username}")
  public Response getUserByUsername(@PathParam("username") String username) {
    LOG.info("Entering getUserByUsername() with username: " + username);
    User user = userRepository.findByUsername(username);
    if (user == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(user).build();
  }

  @POST
  @Transactional
  public Response createUser(@Valid User user) {
    LOG.info("Entering createUser() with username: " + user.getUsername());
    userRepository.persist(user);
    return Response.created(URI.create("/api/users/" + user.getId())).entity(user).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateUser(@PathParam("id") Long id, @Valid User user) {
    LOG.info("Entering updateUser() with id: " + id);
    User existingUser = userRepository.findById(id);
    if (existingUser == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    existingUser.setEmail(user.getEmail());
    existingUser.setRole(user.getRole());
    existingUser.setEnabled(user.getEnabled());
    existingUser.setLastLoginDate(user.getLastLoginDate());
    return Response.ok(existingUser).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteUser(@PathParam("id") Long id) {
    LOG.info("Entering deleteUser() with id: " + id);
    User user = userRepository.findById(id);
    if (user == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    userRepository.delete(user);
    return Response.noContent().build();
  }
}
