package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.repository.SupplierRepository;
import com.pluralsight.persistence.supplier.model.Supplier;
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

import java.net.URI;
import java.util.List;

@Path("/api/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierResource {

  @Inject
  SupplierRepository supplierRepository;

  @GET
  public List<Supplier> getAllSuppliers() {
    return supplierRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getSupplier(@PathParam("id") Long id) {
    Supplier supplier = supplierRepository.findById(id);
    if (supplier == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(supplier).build();
  }

  @POST
  @Transactional
  public Response createSupplier(@Valid Supplier supplier) {
    supplierRepository.persist(supplier);
    return Response.created(URI.create("/api/suppliers/" + supplier.getId())).entity(supplier).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateSupplier(@PathParam("id") Long id, @Valid Supplier supplier) {
    Supplier existingSupplier = supplierRepository.findById(id);
    if (existingSupplier == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingSupplier.setCompanyName(supplier.getCompanyName());
    existingSupplier.setContactName(supplier.getContactName());
    existingSupplier.setContactEmail(supplier.getContactEmail());
    existingSupplier.setCountry(supplier.getCountry());
    return Response.ok(existingSupplier).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteSupplier(@PathParam("id") Long id) {
    Supplier supplier = supplierRepository.findById(id);
    if (supplier == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    supplierRepository.delete(supplier);
    return Response.noContent().build();
  }
}
