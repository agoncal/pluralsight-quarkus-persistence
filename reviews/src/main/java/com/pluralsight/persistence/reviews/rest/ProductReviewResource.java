package com.pluralsight.persistence.reviews.rest;

import com.pluralsight.persistence.reviews.model.ItemType;
import com.pluralsight.persistence.reviews.model.ProductReview;
import com.pluralsight.persistence.reviews.repository.ProductReviewRepository;
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

@Path("/api/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductReviewResource {

  private static final Logger LOG = Logger.getLogger(ProductReviewResource.class);

  @Inject
  ProductReviewRepository productReviewRepository;

  @GET
  public List<ProductReview> findAll() {
    LOG.info("Entering findAll()");
    return productReviewRepository.findAll();
  }

  @GET
  @Path("/{id}")
  public Response findById(@PathParam("id") Long id) {
    LOG.info("Entering findById() with id: " + id);
    return productReviewRepository.findById(id)
      .map(review -> Response.ok(review).build())
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @GET
  @Path("/item/{itemType}/{itemId}")
  public List<ProductReview> findByItem(@PathParam("itemType") ItemType itemType, @PathParam("itemId") Long itemId) {
    LOG.info("Entering findByItem() with itemType: " + itemType + ", itemId: " + itemId);
    return productReviewRepository.findByItem(itemId, itemType);
  }

  @POST
  @Transactional
  public Response create(@Valid ProductReview review) {
    LOG.info("Entering create() with title: " + review.title);
    ProductReview created = productReviewRepository.create(review);
    return Response.created(URI.create("/api/reviews/" + created.id)).entity(created).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response update(@PathParam("id") Long id, @Valid ProductReview review) {
    LOG.info("Entering update() with id: " + id);
    return productReviewRepository.findById(id)
      .map(existing -> {
        existing.username = review.username;
        existing.rating = review.rating;
        existing.title = review.title;
        existing.comment = review.comment;
        ProductReview updated = productReviewRepository.update(existing);
        return Response.ok(updated).build();
      })
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response delete(@PathParam("id") Long id) {
    LOG.info("Entering delete() with id: " + id);
    return productReviewRepository.findById(id)
      .map(review -> {
        productReviewRepository.delete(review);
        return Response.noContent().build();
      })
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }
}
