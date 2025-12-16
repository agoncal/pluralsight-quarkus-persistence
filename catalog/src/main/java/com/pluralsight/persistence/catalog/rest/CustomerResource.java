package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.repository.CustomerRepository;
import com.pluralsight.persistence.customer.model.Customer;
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

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

  private static final Logger LOG = Logger.getLogger(CustomerResource.class);

  @Inject
  CustomerRepository customerRepository;

  @GET
  public List<Customer> getAllCustomers() {
    LOG.info("Entering getAllCustomers()");
    return customerRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getCustomer(@PathParam("id") Long id) {
    LOG.info("Entering getCustomer() with id: " + id);
    Customer customer = customerRepository.findById(id);
    if (customer == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(customer).build();
  }

  @POST
  @Transactional
  public Response createCustomer(@Valid Customer customer) {
    LOG.info("Entering createCustomer() with name: " + customer.getFirstName() + " " + customer.getLastName());
    customerRepository.persist(customer);
    return Response.created(URI.create("/api/customers/" + customer.getId())).entity(customer).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateCustomer(@PathParam("id") Long id, @Valid Customer customer) {
    LOG.info("Entering updateCustomer() with id: " + id);
    Customer existingCustomer = customerRepository.findById(id);
    if (existingCustomer == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingCustomer.setFirstName(customer.getFirstName());
    existingCustomer.setLastName(customer.getLastName());
    existingCustomer.setPhone(customer.getPhone());
    existingCustomer.setBillingAddress(customer.getBillingAddress());
    existingCustomer.setShippingAddress(customer.getShippingAddress());
    existingCustomer.setUser(customer.getUser());
    return Response.ok(existingCustomer).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteCustomer(@PathParam("id") Long id) {
    LOG.info("Entering deleteCustomer() with id: " + id);
    Customer customer = customerRepository.findById(id);
    if (customer == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    customerRepository.delete(customer);
    return Response.noContent().build();
  }
}
