package com.pluralsight.persistence.web;

import com.pluralsight.persistence.web.catalog.BookDTO;
import com.pluralsight.persistence.web.catalog.CDDTO;
import com.pluralsight.persistence.web.catalog.CatalogProxy;
import com.pluralsight.persistence.web.catalog.PurchaseOrderDTO;
import com.pluralsight.persistence.web.catalog.UserDTO;
import com.pluralsight.persistence.web.reviews.ItemType;
import com.pluralsight.persistence.web.reviews.ProductReviewDTO;
import com.pluralsight.persistence.web.reviews.ReviewsProxy;

import java.math.BigDecimal;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestForm;

import java.util.List;

public class WebApplication extends Controller {

  private static final Logger LOG = Logger.getLogger(WebApplication.class);

  @Inject
  @RestClient
  CatalogProxy catalogProxy;

  @Inject
  @RestClient
  ReviewsProxy reviewsProxy;

  @Inject
  UserSession userSession;

  @Path("/")
  public TemplateInstance index() {
    LOG.info("Entering index()");
    return Templates.index();
  }

  @Path("/books")
  public TemplateInstance books(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("10") int size,
      @QueryParam("inStock") Boolean inStock,
      @QueryParam("language") String language,
      @QueryParam("publisher") Long publisherId,
      @QueryParam("sortBy") @DefaultValue("title") String sortBy,
      @QueryParam("sortDir") @DefaultValue("asc") String sortDir) {
    LOG.info("Entering books() with page=" + page + ", size=" + size + ", inStock=" + inStock + ", language=" + language + ", publisher=" + publisherId + ", sortBy=" + sortBy + ", sortDir=" + sortDir);
    List<BookDTO> books = catalogProxy.getAllBooks(page, size, inStock, language, publisherId, sortBy, sortDir);
    return Templates.books(books, page, size, inStock, language, publisherId, sortBy, sortDir);
  }

  @Path("/books/{id}")
  public TemplateInstance book(@PathParam("id") Long id) {
    LOG.info("Entering book() with id: " + id);
    Response response = catalogProxy.getBook(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    BookDTO book = response.readEntity(BookDTO.class);
    List<ProductReviewDTO> reviews = reviewsProxy.findByItem(ItemType.BOOK, id);
    return Templates.book(book, reviews);
  }

  @Path("/cds")
  public TemplateInstance cds(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("10") int size,
      @QueryParam("inStock") Boolean inStock,
      @QueryParam("genre") String genre,
      @QueryParam("label") String label,
      @QueryParam("sortBy") @DefaultValue("title") String sortBy,
      @QueryParam("sortDir") @DefaultValue("asc") String sortDir) {
    LOG.info("Entering cds() with page=" + page + ", size=" + size + ", inStock=" + inStock + ", genre=" + genre + ", label=" + label + ", sortBy=" + sortBy + ", sortDir=" + sortDir);
    List<CDDTO> cds = catalogProxy.getAllCDs(page, size, inStock, genre, label, sortBy, sortDir);
    return Templates.cds(cds, page, size, inStock, genre, label, sortBy, sortDir);
  }

  @Path("/cds/{id}")
  public TemplateInstance cd(@PathParam("id") Long id) {
    LOG.info("Entering cd() with id: " + id);
    Response response = catalogProxy.getCD(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    CDDTO cd = response.readEntity(CDDTO.class);
    List<ProductReviewDTO> reviews = reviewsProxy.findByItem(ItemType.CD, id);
    return Templates.cd(cd, reviews);
  }

  @GET
  @Path("/signin")
  public TemplateInstance signinPage() {
    LOG.info("Entering signinPage()");
    return Templates.signin(null, null, null);
  }

  @POST
  @Path("/signin")
  public TemplateInstance signin(@RestForm String login, @RestForm String password) {
    LOG.info("Entering signin() for user: " + (login != null ? login.trim() : "null"));
    String loginError = null;
    String passwordError = null;

    // Validate inputs
    if (login == null || login.trim().isEmpty()) {
      loginError = "Username is required";
      LOG.warn("Signin failed: Username is required");
    }
    if (password == null || password.trim().isEmpty()) {
      passwordError = "Password is required";
      LOG.warn("Signin failed: Password is required");
    }

    if (loginError != null || passwordError != null) {
      return Templates.signin(loginError, passwordError, login);
    }

    // Find user by username from catalog service
    UserDTO user;
    try {
      Response response = catalogProxy.getUserByUsername(login.trim());
      if (response.getStatus() == 404) {
        loginError = "User not found";
        LOG.warn("Signin failed: User not found - " + login.trim());
        return Templates.signin(loginError, passwordError, login);
      }
      user = response.readEntity(UserDTO.class);
    } catch (Exception e) {
      LOG.error("Error during signin", e);
      loginError = "An error occurred. Please try again.";
      return Templates.signin(loginError, passwordError, login);
    }

    // For demo purposes, accept any password
    // In production, you would verify: passwordEncoder.matches(password, user.passwordHash)

    // Store user in session
    userSession.setCurrentUser(user);
    LOG.info("Successful signin for user: " + user.username + " (" + user.role + ")");

    // Redirect to home page after successful login
    redirect(WebApplication.class).index();
    return null; // Never reached, redirect throws exception
  }

  @Path("/orders")
  public TemplateInstance orders() {
    LOG.info("Entering orders()");
    if (!userSession.isLoggedIn()) {
      redirect(WebApplication.class).signinPage();
      return null;
    }
    String username = userSession.getUsername();
    List<PurchaseOrderDTO> orders = catalogProxy.getPurchaseOrdersByUsername(username);
    return Templates.orders(orders);
  }

  // Cart endpoints

  @Path("/cart")
  public TemplateInstance cart() {
    LOG.info("Entering cart()");
    return Templates.cart(userSession.getCart(), userSession.getCartTotal());
  }

  @POST
  @Path("/cart/add/book/{id}")
  public void addBookToCart(@PathParam("id") Long id) {
    LOG.info("Entering addBookToCart() with id: " + id);
    Response response = catalogProxy.getBook(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    BookDTO book = response.readEntity(BookDTO.class);
    userSession.addToCart(book.id, book.title, book.price, CartItem.ItemType.BOOK);
    LOG.info("Added book to cart: " + book.title);
    redirect(WebApplication.class).cart();
  }

  @POST
  @Path("/cart/add/cd/{id}")
  public void addCDToCart(@PathParam("id") Long id) {
    LOG.info("Entering addCDToCart() with id: " + id);
    Response response = catalogProxy.getCD(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    CDDTO cd = response.readEntity(CDDTO.class);
    userSession.addToCart(cd.id, cd.title, cd.price, CartItem.ItemType.CD);
    LOG.info("Added CD to cart: " + cd.title);
    redirect(WebApplication.class).cart();
  }

  @POST
  @Path("/cart/remove/book/{id}")
  public void removeBookFromCart(@PathParam("id") Long id) {
    LOG.info("Entering removeBookFromCart() with id: " + id);
    userSession.removeFromCart(id, CartItem.ItemType.BOOK);
    redirect(WebApplication.class).cart();
  }

  @POST
  @Path("/cart/remove/cd/{id}")
  public void removeCDFromCart(@PathParam("id") Long id) {
    LOG.info("Entering removeCDFromCart() with id: " + id);
    userSession.removeFromCart(id, CartItem.ItemType.CD);
    redirect(WebApplication.class).cart();
  }

  @POST
  @Path("/cart/clear")
  public void clearCart() {
    LOG.info("Entering clearCart()");
    userSession.clearCart();
    redirect(WebApplication.class).cart();
  }

  @Path("/logout")
  public void logout() {
    LOG.info("Entering logout()");
    UserDTO currentUser = userSession.getCurrentUser();
    if (currentUser != null) {
      LOG.info("User logout: " + currentUser.username);
    }
    userSession.logout();
    redirect(WebApplication.class).index();
  }

  @CheckedTemplate
  static class Templates {
    public static native TemplateInstance index();

    public static native TemplateInstance books(List<BookDTO> books, int page, int size, Boolean inStock, String language, Long publisherId, String sortBy, String sortDir);

    public static native TemplateInstance book(BookDTO book, List<ProductReviewDTO> reviews);

    public static native TemplateInstance cds(List<CDDTO> cds, int page, int size, Boolean inStock, String genre, String label, String sortBy, String sortDir);

    public static native TemplateInstance cd(CDDTO cd, List<ProductReviewDTO> reviews);

    public static native TemplateInstance signin(String loginError, String passwordError, String login);

    public static native TemplateInstance orders(List<PurchaseOrderDTO> orders);

    public static native TemplateInstance cart(List<CartItem> items, BigDecimal total);
  }
}
