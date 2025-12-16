package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.repository.PurchaseOrderRepository;
import com.pluralsight.persistence.customer.model.PurchaseOrder;
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

@Path("/api/pos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseOrderResource {

  private static final Logger LOG = Logger.getLogger(PurchaseOrderResource.class);

  @Inject
  PurchaseOrderRepository purchaseOrderRepository;

  @GET
  public List<PurchaseOrder> getAllPurchaseOrders() {
    LOG.info("Entering getAllPurchaseOrders()");
    return purchaseOrderRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getPurchaseOrder(@PathParam("id") Long id) {
    LOG.info("Entering getPurchaseOrder() with id: " + id);
    PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id);
    if (purchaseOrder == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(purchaseOrder).build();
  }

  @GET
  @Path("/username/{username}")
  public List<PurchaseOrder> getPurchaseOrdersByUsername(@PathParam("username") String username) {
    LOG.info("Entering getPurchaseOrdersByUsername() with username: " + username);
    return purchaseOrderRepository.findByUsername(username);
  }

  @POST
  @Transactional
  public Response createPurchaseOrder(@Valid PurchaseOrder purchaseOrder) {
    LOG.info("Entering createPurchaseOrder()");
    purchaseOrderRepository.persist(purchaseOrder);
    return Response.created(URI.create("/api/pos/" + purchaseOrder.getId())).entity(purchaseOrder).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updatePurchaseOrder(@PathParam("id") Long id, @Valid PurchaseOrder purchaseOrder) {
    LOG.info("Entering updatePurchaseOrder() with id: " + id);
    PurchaseOrder existingOrder = purchaseOrderRepository.findById(id);
    if (existingOrder == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingOrder.setOrderDate(purchaseOrder.getOrderDate());
    existingOrder.setStatus(purchaseOrder.getStatus());
    existingOrder.setTotalAmount(purchaseOrder.getTotalAmount());
    existingOrder.setCustomer(purchaseOrder.getCustomer());
    existingOrder.setShippingAddress(purchaseOrder.getShippingAddress());
    return Response.ok(existingOrder).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deletePurchaseOrder(@PathParam("id") Long id) {
    LOG.info("Entering deletePurchaseOrder() with id: " + id);
    PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id);
    if (purchaseOrder == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    purchaseOrderRepository.delete(purchaseOrder);
    return Response.noContent().build();
  }
}
