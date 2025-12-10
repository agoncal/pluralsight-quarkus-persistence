package com.pluralsight.persistence.web;

import com.pluralsight.persistence.web.activity.ActivityProxy;
import com.pluralsight.persistence.web.activity.UserActivityLogDTO;
import com.pluralsight.persistence.web.catalog.*;
import com.pluralsight.persistence.web.reviews.ProductReviewDTO;
import com.pluralsight.persistence.web.reviews.ReviewsProxy;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Path("/admin")
public class AdminController extends Controller {

  private static final Logger LOG = Logger.getLogger(AdminController.class);

  @Inject
  @RestClient
  CatalogProxy catalogProxy;

  @Inject
  @RestClient
  ActivityProxy activityProxy;

  @Inject
  @RestClient
  ReviewsProxy reviewsProxy;

  // ======================================
  // Admin Index
  // ======================================

  @GET
  @Path("")
  public TemplateInstance index() {
    LOG.info("Entering admin index()");
    return Templates.index();
  }

  // ======================================
  // Authors CRUD
  // ======================================

  @GET
  @Path("/authors")
  public TemplateInstance authors() {
    LOG.info("Entering admin authors()");
    List<AuthorDTO> authors = catalogProxy.getAllAuthors();
    return Templates.authors(authors);
  }

  @GET
  @Path("/authors/{id:\\d+}")
  public TemplateInstance author(@PathParam("id") Long id) {
    LOG.info("Entering admin author() with id: " + id);
    Response response = catalogProxy.getAuthor(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    AuthorDTO author = response.readEntity(AuthorDTO.class);
    return Templates.author(author);
  }

  @GET
  @Path("/authors/new")
  public TemplateInstance authorNew() {
    LOG.info("Entering admin authorNew()");
    return Templates.authorForm(null, Language.values());
  }

  @GET
  @Path("/authors/{id:\\d+}/edit")
  public TemplateInstance authorEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin authorEdit() with id: " + id);
    Response response = catalogProxy.getAuthor(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    AuthorDTO author = response.readEntity(AuthorDTO.class);
    return Templates.authorForm(author, Language.values());
  }

  @POST
  @Path("/authors/save")
  public Response authorSave(
      @RestForm Long id,
      @RestForm String firstName,
      @RestForm String lastName,
      @RestForm String bio,
      @RestForm LocalDate dateOfBirth,
      @RestForm Language preferredLanguage,
      @RestForm String website) {
    LOG.info("Entering admin authorSave()");

    AuthorDTO author = new AuthorDTO();
    author.firstName = (firstName != null && !firstName.isBlank()) ? firstName : null;
    author.lastName = (lastName != null && !lastName.isBlank()) ? lastName : null;
    author.bio = (bio != null && !bio.isBlank()) ? bio : null;
    author.dateOfBirth = dateOfBirth;
    author.preferredLanguage = preferredLanguage;
    author.website = (website != null && !website.isBlank()) ? website : null;

    if (id != null) {
      author.id = id;
      catalogProxy.updateAuthor(id, author);
    } else {
      catalogProxy.createAuthor(author);
    }

    return Response.seeOther(URI.create("/admin/authors")).build();
  }

  @GET
  @Path("/authors/{id:\\d+}/delete")
  public Response authorDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin authorDelete() with id: " + id);
    catalogProxy.deleteAuthor(id);
    return Response.seeOther(URI.create("/admin/authors")).build();
  }

  // ======================================
  // Books CRUD
  // ======================================

  @GET
  @Path("/books")
  public TemplateInstance books() {
    LOG.info("Entering admin books()");
    List<BookDTO> books = catalogProxy.getAllBooks();
    return Templates.books(books);
  }

  @GET
  @Path("/books/{id:\\d+}")
  public TemplateInstance book(@PathParam("id") Long id) {
    LOG.info("Entering admin book() with id: " + id);
    Response response = catalogProxy.getBook(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    BookDTO book = response.readEntity(BookDTO.class);
    return Templates.book(book);
  }

  @GET
  @Path("/books/new")
  public TemplateInstance bookNew() {
    LOG.info("Entering admin bookNew()");
    List<PublisherDTO> publishers = catalogProxy.getAllPublishers();
    List<AuthorDTO> authors = catalogProxy.getAllAuthors();
    return Templates.bookForm(null, publishers, authors, Language.values());
  }

  @GET
  @Path("/books/{id:\\d+}/edit")
  public TemplateInstance bookEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin bookEdit() with id: " + id);
    Response response = catalogProxy.getBook(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    BookDTO book = response.readEntity(BookDTO.class);
    List<PublisherDTO> publishers = catalogProxy.getAllPublishers();
    List<AuthorDTO> authors = catalogProxy.getAllAuthors();
    return Templates.bookForm(book, publishers, authors, Language.values());
  }

  @POST
  @Path("/books/save")
  public Response bookSave(
      @RestForm Long id,
      @RestForm String title,
      @RestForm String description,
      @RestForm BigDecimal price,
      @RestForm Integer stock,
      @RestForm String isbn,
      @RestForm Integer nbOfPages,
      @RestForm LocalDate publicationDate,
      @RestForm Language language,
      @RestForm Long publisherId) {
    LOG.info("Entering admin bookSave()");

    BookDTO book = new BookDTO();
    book.title = (title != null && !title.isBlank()) ? title : null;
    book.description = (description != null && !description.isBlank()) ? description : null;
    book.price = price;
    book.stock = stock;
    book.isbn = (isbn != null && !isbn.isBlank()) ? isbn : null;
    book.nbOfPages = nbOfPages;
    book.publicationDate = publicationDate;
    book.language = language;
    if (publisherId != null) {
      PublisherDTO publisher = new PublisherDTO();
      publisher.id = publisherId;
      book.publisher = publisher;
    }

    if (id != null) {
      book.id = id;
      catalogProxy.updateBook(id, book);
    } else {
      catalogProxy.createBook(book);
    }

    return Response.seeOther(URI.create("/admin/books")).build();
  }

  @GET
  @Path("/books/{id:\\d+}/delete")
  public Response bookDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin bookDelete() with id: " + id);
    catalogProxy.deleteBook(id);
    return Response.seeOther(URI.create("/admin/books")).build();
  }

  // ======================================
  // CDs CRUD
  // ======================================

  @GET
  @Path("/cds")
  public TemplateInstance cds() {
    LOG.info("Entering admin cds()");
    List<CDDTO> cds = catalogProxy.getAllCDs();
    return Templates.cds(cds);
  }

  @GET
  @Path("/cds/{id:\\d+}")
  public TemplateInstance cd(@PathParam("id") Long id) {
    LOG.info("Entering admin cd() with id: " + id);
    Response response = catalogProxy.getCD(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    CDDTO cd = response.readEntity(CDDTO.class);
    return Templates.cd(cd);
  }

  @GET
  @Path("/cds/new")
  public TemplateInstance cdNew() {
    LOG.info("Entering admin cdNew()");
    List<MusicianDTO> musicians = catalogProxy.getAllMusicians();
    return Templates.cdForm(null, musicians, MusicGenre.values());
  }

  @GET
  @Path("/cds/{id:\\d+}/edit")
  public TemplateInstance cdEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin cdEdit() with id: " + id);
    Response response = catalogProxy.getCD(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    CDDTO cd = response.readEntity(CDDTO.class);
    List<MusicianDTO> musicians = catalogProxy.getAllMusicians();
    return Templates.cdForm(cd, musicians, MusicGenre.values());
  }

  @POST
  @Path("/cds/save")
  public Response cdSave(
      @RestForm Long id,
      @RestForm String title,
      @RestForm String description,
      @RestForm BigDecimal price,
      @RestForm Integer stock,
      @RestForm String ean,
      @RestForm String musicCompany,
      @RestForm MusicGenre genre,
      @RestForm LocalDate releaseDate) {
    LOG.info("Entering admin cdSave()");

    CDDTO cd = new CDDTO();
    cd.title = (title != null && !title.isBlank()) ? title : null;
    cd.description = (description != null && !description.isBlank()) ? description : null;
    cd.price = price;
    cd.stock = stock;
    cd.ean = (ean != null && !ean.isBlank()) ? ean : null;
    cd.musicCompany = (musicCompany != null && !musicCompany.isBlank()) ? musicCompany : null;
    cd.genre = genre;
    cd.releaseDate = releaseDate;

    if (id != null) {
      cd.id = id;
      catalogProxy.updateCD(id, cd);
    } else {
      catalogProxy.createCD(cd);
    }

    return Response.seeOther(URI.create("/admin/cds")).build();
  }

  @GET
  @Path("/cds/{id:\\d+}/delete")
  public Response cdDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin cdDelete() with id: " + id);
    catalogProxy.deleteCD(id);
    return Response.seeOther(URI.create("/admin/cds")).build();
  }

  // ======================================
  // Customers CRUD
  // ======================================

  @GET
  @Path("/customers")
  public TemplateInstance customers() {
    LOG.info("Entering admin customers()");
    List<CustomerDTO> customers = catalogProxy.getAllCustomers();
    return Templates.customers(customers);
  }

  @GET
  @Path("/customers/{id:\\d+}")
  public TemplateInstance customer(@PathParam("id") Long id) {
    LOG.info("Entering admin customer() with id: " + id);
    Response response = catalogProxy.getCustomer(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    CustomerDTO customer = response.readEntity(CustomerDTO.class);
    return Templates.customer(customer);
  }

  @GET
  @Path("/customers/new")
  public TemplateInstance customerNew() {
    LOG.info("Entering admin customerNew()");
    List<UserDTO> users = catalogProxy.getAllUsers();
    return Templates.customerForm(null, users);
  }

  @GET
  @Path("/customers/{id:\\d+}/edit")
  public TemplateInstance customerEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin customerEdit() with id: " + id);
    Response response = catalogProxy.getCustomer(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    CustomerDTO customer = response.readEntity(CustomerDTO.class);
    List<UserDTO> users = catalogProxy.getAllUsers();
    return Templates.customerForm(customer, users);
  }

  @POST
  @Path("/customers/save")
  public Response customerSave(
      @RestForm Long id,
      @RestForm String firstName,
      @RestForm String lastName,
      @RestForm String phone,
      @RestForm Long userId) {
    LOG.info("Entering admin customerSave()");

    CustomerDTO customer = new CustomerDTO();
    customer.firstName = (firstName != null && !firstName.isBlank()) ? firstName : null;
    customer.lastName = (lastName != null && !lastName.isBlank()) ? lastName : null;
    customer.phone = (phone != null && !phone.isBlank()) ? phone : null;
    if (userId != null) {
      UserDTO user = new UserDTO();
      user.id = userId;
      customer.user = user;
    }

    if (id != null) {
      customer.id = id;
      catalogProxy.updateCustomer(id, customer);
    } else {
      catalogProxy.createCustomer(customer);
    }

    return Response.seeOther(URI.create("/admin/customers")).build();
  }

  @GET
  @Path("/customers/{id:\\d+}/delete")
  public Response customerDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin customerDelete() with id: " + id);
    catalogProxy.deleteCustomer(id);
    return Response.seeOther(URI.create("/admin/customers")).build();
  }

  // ======================================
  // Musicians CRUD
  // ======================================

  @GET
  @Path("/musicians")
  public TemplateInstance musicians() {
    LOG.info("Entering admin musicians()");
    List<MusicianDTO> musicians = catalogProxy.getAllMusicians();
    return Templates.musicians(musicians);
  }

  @GET
  @Path("/musicians/{id:\\d+}")
  public TemplateInstance musician(@PathParam("id") Long id) {
    LOG.info("Entering admin musician() with id: " + id);
    Response response = catalogProxy.getMusician(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    MusicianDTO musician = response.readEntity(MusicianDTO.class);
    return Templates.musician(musician);
  }

  @GET
  @Path("/musicians/new")
  public TemplateInstance musicianNew() {
    LOG.info("Entering admin musicianNew()");
    return Templates.musicianForm(null);
  }

  @GET
  @Path("/musicians/{id:\\d+}/edit")
  public TemplateInstance musicianEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin musicianEdit() with id: " + id);
    Response response = catalogProxy.getMusician(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    MusicianDTO musician = response.readEntity(MusicianDTO.class);
    return Templates.musicianForm(musician);
  }

  @POST
  @Path("/musicians/save")
  public Response musicianSave(
      @RestForm Long id,
      @RestForm String firstName,
      @RestForm String lastName,
      @RestForm String bio,
      @RestForm LocalDate dateOfBirth,
      @RestForm String stageName,
      @RestForm String instrument) {
    LOG.info("Entering admin musicianSave()");

    MusicianDTO musician = new MusicianDTO();
    musician.firstName = (firstName != null && !firstName.isBlank()) ? firstName : null;
    musician.lastName = (lastName != null && !lastName.isBlank()) ? lastName : null;
    musician.bio = (bio != null && !bio.isBlank()) ? bio : null;
    musician.dateOfBirth = dateOfBirth;
    musician.stageName = (stageName != null && !stageName.isBlank()) ? stageName : null;
    musician.instrument = (instrument != null && !instrument.isBlank()) ? instrument : null;

    if (id != null) {
      musician.id = id;
      catalogProxy.updateMusician(id, musician);
    } else {
      catalogProxy.createMusician(musician);
    }

    return Response.seeOther(URI.create("/admin/musicians")).build();
  }

  @GET
  @Path("/musicians/{id:\\d+}/delete")
  public Response musicianDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin musicianDelete() with id: " + id);
    catalogProxy.deleteMusician(id);
    return Response.seeOther(URI.create("/admin/musicians")).build();
  }

  // ======================================
  // Publishers CRUD
  // ======================================

  @GET
  @Path("/publishers")
  public TemplateInstance publishers() {
    LOG.info("Entering admin publishers()");
    List<PublisherDTO> publishers = catalogProxy.getAllPublishers();
    return Templates.publishers(publishers);
  }

  @GET
  @Path("/publishers/{id:\\d+}")
  public TemplateInstance publisher(@PathParam("id") Long id) {
    LOG.info("Entering admin publisher() with id: " + id);
    Response response = catalogProxy.getPublisher(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    PublisherDTO publisher = response.readEntity(PublisherDTO.class);
    return Templates.publisher(publisher);
  }

  @GET
  @Path("/publishers/new")
  public TemplateInstance publisherNew() {
    LOG.info("Entering admin publisherNew()");
    return Templates.publisherForm(null);
  }

  @GET
  @Path("/publishers/{id:\\d+}/edit")
  public TemplateInstance publisherEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin publisherEdit() with id: " + id);
    Response response = catalogProxy.getPublisher(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    PublisherDTO publisher = response.readEntity(PublisherDTO.class);
    return Templates.publisherForm(publisher);
  }

  @POST
  @Path("/publishers/save")
  public Response publisherSave(
      @RestForm Long id,
      @RestForm String name,
      @RestForm String country,
      @RestForm String website,
      @RestForm Integer foundedYear) {
    LOG.info("Entering admin publisherSave()");

    PublisherDTO publisher = new PublisherDTO();
    publisher.name = (name != null && !name.isBlank()) ? name : null;
    publisher.country = (country != null && !country.isBlank()) ? country : null;
    publisher.website = (website != null && !website.isBlank()) ? website : null;
    publisher.foundedYear = foundedYear;

    if (id != null) {
      publisher.id = id;
      catalogProxy.updatePublisher(id, publisher);
    } else {
      catalogProxy.createPublisher(publisher);
    }

    return Response.seeOther(URI.create("/admin/publishers")).build();
  }

  @GET
  @Path("/publishers/{id:\\d+}/delete")
  public Response publisherDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin publisherDelete() with id: " + id);
    catalogProxy.deletePublisher(id);
    return Response.seeOther(URI.create("/admin/publishers")).build();
  }

  // ======================================
  // Purchase Orders CRUD
  // ======================================

  @GET
  @Path("/orders")
  public TemplateInstance orders() {
    LOG.info("Entering admin orders()");
    List<PurchaseOrderDTO> orders = catalogProxy.getAllPurchaseOrders();
    return Templates.orders(orders);
  }

  @GET
  @Path("/orders/{id:\\d+}")
  public TemplateInstance order(@PathParam("id") Long id) {
    LOG.info("Entering admin order() with id: " + id);
    Response response = catalogProxy.getPurchaseOrder(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    PurchaseOrderDTO order = response.readEntity(PurchaseOrderDTO.class);
    return Templates.order(order);
  }

  @GET
  @Path("/orders/new")
  public TemplateInstance orderNew() {
    LOG.info("Entering admin orderNew()");
    List<CustomerDTO> customers = catalogProxy.getAllCustomers();
    return Templates.orderForm(null, customers, OrderStatus.values());
  }

  @GET
  @Path("/orders/{id:\\d+}/edit")
  public TemplateInstance orderEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin orderEdit() with id: " + id);
    Response response = catalogProxy.getPurchaseOrder(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    PurchaseOrderDTO order = response.readEntity(PurchaseOrderDTO.class);
    List<CustomerDTO> customers = catalogProxy.getAllCustomers();
    return Templates.orderForm(order, customers, OrderStatus.values());
  }

  @POST
  @Path("/orders/save")
  public Response orderSave(
      @RestForm Long id,
      @RestForm LocalDateTime orderDate,
      @RestForm OrderStatus status,
      @RestForm BigDecimal totalAmount,
      @RestForm Long customerId) {
    LOG.info("Entering admin orderSave()");

    PurchaseOrderDTO order = new PurchaseOrderDTO();
    order.orderDate = orderDate;
    order.status = status;
    order.totalAmount = totalAmount;
    if (customerId != null) {
      CustomerDTO customer = new CustomerDTO();
      customer.id = customerId;
      order.customer = customer;
    }

    if (id != null) {
      order.id = id;
      catalogProxy.updatePurchaseOrder(id, order);
    } else {
      catalogProxy.createPurchaseOrder(order);
    }

    return Response.seeOther(URI.create("/admin/orders")).build();
  }

  @GET
  @Path("/orders/{id:\\d+}/delete")
  public Response orderDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin orderDelete() with id: " + id);
    catalogProxy.deletePurchaseOrder(id);
    return Response.seeOther(URI.create("/admin/orders")).build();
  }

  // ======================================
  // Users CRUD
  // ======================================

  @GET
  @Path("/users")
  public TemplateInstance users() {
    LOG.info("Entering admin users()");
    List<UserDTO> users = catalogProxy.getAllUsers();
    return Templates.users(users);
  }

  @GET
  @Path("/users/{id:\\d+}")
  public TemplateInstance user(@PathParam("id") Long id) {
    LOG.info("Entering admin user() with id: " + id);
    Response response = catalogProxy.getUser(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    UserDTO user = response.readEntity(UserDTO.class);
    return Templates.user(user);
  }

  @GET
  @Path("/users/new")
  public TemplateInstance userNew() {
    LOG.info("Entering admin userNew()");
    return Templates.userForm(null, UserRole.values());
  }

  @GET
  @Path("/users/{id:\\d+}/edit")
  public TemplateInstance userEdit(@PathParam("id") Long id) {
    LOG.info("Entering admin userEdit() with id: " + id);
    Response response = catalogProxy.getUser(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    UserDTO user = response.readEntity(UserDTO.class);
    return Templates.userForm(user, UserRole.values());
  }

  @POST
  @Path("/users/save")
  public Response userSave(
      @RestForm Long id,
      @RestForm String username,
      @RestForm String email,
      @RestForm UserRole role,
      @RestForm Boolean enabled) {
    LOG.info("Entering admin userSave()");

    UserDTO user = new UserDTO();
    user.username = (username != null && !username.isBlank()) ? username : null;
    user.email = (email != null && !email.isBlank()) ? email : null;
    user.role = role;
    user.enabled = enabled != null ? enabled : true;

    if (id != null) {
      user.id = id;
      catalogProxy.updateUser(id, user);
    } else {
      catalogProxy.createUser(user);
    }

    return Response.seeOther(URI.create("/admin/users")).build();
  }

  @GET
  @Path("/users/{id:\\d+}/delete")
  public Response userDelete(@PathParam("id") Long id) {
    LOG.info("Entering admin userDelete() with id: " + id);
    catalogProxy.deleteUser(id);
    return Response.seeOther(URI.create("/admin/users")).build();
  }

  // ======================================
  // User Activities (Read-only)
  // ======================================

  @GET
  @Path("/activities")
  public TemplateInstance activities() {
    LOG.info("Entering admin activities()");
    List<UserActivityLogDTO> activities = activityProxy.getAll();
    return Templates.activities(activities);
  }

  // ======================================
  // Product Reviews (Read-only)
  // ======================================

  @GET
  @Path("/reviews")
  public TemplateInstance reviews() {
    LOG.info("Entering admin reviews()");
    List<ProductReviewDTO> reviews = reviewsProxy.findAll();
    return Templates.reviews(reviews);
  }

  // ======================================
  // Templates
  // ======================================

  @CheckedTemplate(basePath = "AdminController")
  static class Templates {
    // Index
    public static native TemplateInstance index();

    // Authors
    public static native TemplateInstance authors(List<AuthorDTO> authors);
    public static native TemplateInstance author(AuthorDTO author);
    public static native TemplateInstance authorForm(AuthorDTO author, Language[] languages);

    // Books
    public static native TemplateInstance books(List<BookDTO> books);
    public static native TemplateInstance book(BookDTO book);
    public static native TemplateInstance bookForm(BookDTO book, List<PublisherDTO> publishers, List<AuthorDTO> authors, Language[] languages);

    // CDs
    public static native TemplateInstance cds(List<CDDTO> cds);
    public static native TemplateInstance cd(CDDTO cd);
    public static native TemplateInstance cdForm(CDDTO cd, List<MusicianDTO> musicians, MusicGenre[] genres);

    // Customers
    public static native TemplateInstance customers(List<CustomerDTO> customers);
    public static native TemplateInstance customer(CustomerDTO customer);
    public static native TemplateInstance customerForm(CustomerDTO customer, List<UserDTO> users);

    // Musicians
    public static native TemplateInstance musicians(List<MusicianDTO> musicians);
    public static native TemplateInstance musician(MusicianDTO musician);
    public static native TemplateInstance musicianForm(MusicianDTO musician);

    // Publishers
    public static native TemplateInstance publishers(List<PublisherDTO> publishers);
    public static native TemplateInstance publisher(PublisherDTO publisher);
    public static native TemplateInstance publisherForm(PublisherDTO publisher);

    // Orders
    public static native TemplateInstance orders(List<PurchaseOrderDTO> orders);
    public static native TemplateInstance order(PurchaseOrderDTO order);
    public static native TemplateInstance orderForm(PurchaseOrderDTO order, List<CustomerDTO> customers, OrderStatus[] statuses);

    // Users
    public static native TemplateInstance users(List<UserDTO> users);
    public static native TemplateInstance user(UserDTO user);
    public static native TemplateInstance userForm(UserDTO user, UserRole[] roles);

    // Activities
    public static native TemplateInstance activities(List<UserActivityLogDTO> activities);

    // Reviews
    public static native TemplateInstance reviews(List<ProductReviewDTO> reviews);
  }
}
