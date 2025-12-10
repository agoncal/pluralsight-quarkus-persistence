package com.pluralsight.persistence.web.reviews;

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

@RegisterRestClient(configKey = "reviews")
@Path("/api/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ReviewsProxy {

  @GET
  List<ProductReviewDTO> findAll();

  @GET
  @Path("/{id}")
  Response findById(@PathParam("id") Long id);

  @GET
  @Path("/item/{itemType}/{itemId}")
  List<ProductReviewDTO> findByItem(@PathParam("itemType") ItemType itemType, @PathParam("itemId") Long itemId);

  @POST
  Response create(@Valid ProductReviewDTO review);

  @PUT
  @Path("/{id}")
  Response update(@PathParam("id") Long id, @Valid ProductReviewDTO review);

  @DELETE
  @Path("/{id}")
  Response delete(@PathParam("id") Long id);
}
