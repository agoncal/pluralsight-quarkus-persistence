package com.pluralsight.persistence.web.catalog;

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

@RegisterRestClient(configKey = "catalog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CatalogProxy {

  // ======================================
  // Books
  // ======================================

  @GET
  @Path("/api/books")
  List<BookDTO> getAllBooks();

  @GET
  @Path("/api/books/{id}")
  Response getBook(@PathParam("id") Long id);

  @POST
  @Path("/api/books")
  Response createBook(@Valid BookDTO book);

  @PUT
  @Path("/api/books/{id}")
  Response updateBook(@PathParam("id") Long id, @Valid BookDTO book);

  @DELETE
  @Path("/api/books/{id}")
  Response deleteBook(@PathParam("id") Long id);

  // ======================================
  // CDs
  // ======================================

  @GET
  @Path("/api/cds")
  List<CDDTO> getAllCDs();

  @GET
  @Path("/api/cds/{id}")
  Response getCD(@PathParam("id") Long id);

  @POST
  @Path("/api/cds")
  Response createCD(@Valid CDDTO cd);

  @PUT
  @Path("/api/cds/{id}")
  Response updateCD(@PathParam("id") Long id, @Valid CDDTO cd);

  @DELETE
  @Path("/api/cds/{id}")
  Response deleteCD(@PathParam("id") Long id);

  // ======================================
  // Authors
  // ======================================

  @GET
  @Path("/api/authors")
  List<AuthorDTO> getAllAuthors();

  @GET
  @Path("/api/authors/{id}")
  Response getAuthor(@PathParam("id") Long id);

  @POST
  @Path("/api/authors")
  Response createAuthor(@Valid AuthorDTO author);

  @PUT
  @Path("/api/authors/{id}")
  Response updateAuthor(@PathParam("id") Long id, @Valid AuthorDTO author);

  @DELETE
  @Path("/api/authors/{id}")
  Response deleteAuthor(@PathParam("id") Long id);

  // ======================================
  // Musicians
  // ======================================

  @GET
  @Path("/api/musicians")
  List<MusicianDTO> getAllMusicians();

  @GET
  @Path("/api/musicians/{id}")
  Response getMusician(@PathParam("id") Long id);

  @POST
  @Path("/api/musicians")
  Response createMusician(@Valid MusicianDTO musician);

  @PUT
  @Path("/api/musicians/{id}")
  Response updateMusician(@PathParam("id") Long id, @Valid MusicianDTO musician);

  @DELETE
  @Path("/api/musicians/{id}")
  Response deleteMusician(@PathParam("id") Long id);

  // ======================================
  // Publishers
  // ======================================

  @GET
  @Path("/api/publishers")
  List<PublisherDTO> getAllPublishers();

  @GET
  @Path("/api/publishers/{id}")
  Response getPublisher(@PathParam("id") Long id);

  @POST
  @Path("/api/publishers")
  Response createPublisher(@Valid PublisherDTO publisher);

  @PUT
  @Path("/api/publishers/{id}")
  Response updatePublisher(@PathParam("id") Long id, @Valid PublisherDTO publisher);

  @DELETE
  @Path("/api/publishers/{id}")
  Response deletePublisher(@PathParam("id") Long id);

  // ======================================
  // Customers
  // ======================================

  @GET
  @Path("/api/customers")
  List<CustomerDTO> getAllCustomers();

  @GET
  @Path("/api/customers/{id}")
  Response getCustomer(@PathParam("id") Long id);

  @POST
  @Path("/api/customers")
  Response createCustomer(@Valid CustomerDTO customer);

  @PUT
  @Path("/api/customers/{id}")
  Response updateCustomer(@PathParam("id") Long id, @Valid CustomerDTO customer);

  @DELETE
  @Path("/api/customers/{id}")
  Response deleteCustomer(@PathParam("id") Long id);

  // ======================================
  // Purchase Orders
  // ======================================

  @GET
  @Path("/api/pos")
  List<PurchaseOrderDTO> getAllPurchaseOrders();

  @GET
  @Path("/api/pos/{id}")
  Response getPurchaseOrder(@PathParam("id") Long id);

  @POST
  @Path("/api/pos")
  Response createPurchaseOrder(@Valid PurchaseOrderDTO purchaseOrder);

  @PUT
  @Path("/api/pos/{id}")
  Response updatePurchaseOrder(@PathParam("id") Long id, @Valid PurchaseOrderDTO purchaseOrder);

  @DELETE
  @Path("/api/pos/{id}")
  Response deletePurchaseOrder(@PathParam("id") Long id);

  // ======================================
  // Suppliers
  // ======================================

  @GET
  @Path("/api/suppliers")
  List<SupplierDTO> getAllSuppliers();

  @GET
  @Path("/api/suppliers/{id}")
  Response getSupplier(@PathParam("id") Long id);

  @POST
  @Path("/api/suppliers")
  Response createSupplier(@Valid SupplierDTO supplier);

  @PUT
  @Path("/api/suppliers/{id}")
  Response updateSupplier(@PathParam("id") Long id, @Valid SupplierDTO supplier);

  @DELETE
  @Path("/api/suppliers/{id}")
  Response deleteSupplier(@PathParam("id") Long id);

  // ======================================
  // Users
  // ======================================

  @GET
  @Path("/api/users")
  List<UserDTO> getAllUsers();

  @GET
  @Path("/api/users/{id}")
  Response getUser(@PathParam("id") Long id);

  @GET
  @Path("/api/users/username/{username}")
  Response getUserByUsername(@PathParam("username") String username);

  @POST
  @Path("/api/users")
  Response createUser(@Valid UserDTO user);

  @PUT
  @Path("/api/users/{id}")
  Response updateUser(@PathParam("id") Long id, @Valid UserDTO user);

  @DELETE
  @Path("/api/users/{id}")
  Response deleteUser(@PathParam("id") Long id);
}
