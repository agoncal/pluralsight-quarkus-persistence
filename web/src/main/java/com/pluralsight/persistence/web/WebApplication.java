package com.pluralsight.persistence.web;

import com.pluralsight.persistence.web.catalog.BookDTO;
import com.pluralsight.persistence.web.catalog.CDDTO;
import com.pluralsight.persistence.web.catalog.CatalogProxy;
import com.pluralsight.persistence.web.catalog.UserDTO;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
  UserSession userSession;

  @Path("/")
  public TemplateInstance index() {
    LOG.info("Entering index()");
    return Templates.index();
  }

  @Path("/books")
  public TemplateInstance books() {
    LOG.info("Entering books()");
    List<BookDTO> books = catalogProxy.getAllBooks();
    return Templates.books(books);
  }

  @Path("/books/{id}")
  public TemplateInstance book(@PathParam("id") Long id) {
    LOG.info("Entering book() with id: " + id);
    Response response = catalogProxy.getBook(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    BookDTO book = response.readEntity(BookDTO.class);
    return Templates.book(book);
  }

  @Path("/cds")
  public TemplateInstance cds() {
    LOG.info("Entering cds()");
    List<CDDTO> cds = catalogProxy.getAllCDs();
    return Templates.cds(cds);
  }

  @Path("/cds/{id}")
  public TemplateInstance cd(@PathParam("id") Long id) {
    LOG.info("Entering cd() with id: " + id);
    Response response = catalogProxy.getCD(id);
    if (response.getStatus() == 404) {
      notFound();
    }
    CDDTO cd = response.readEntity(CDDTO.class);
    return Templates.cd(cd);
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

    public static native TemplateInstance books(List<BookDTO> books);

    public static native TemplateInstance book(BookDTO book);

    public static native TemplateInstance cds(List<CDDTO> cds);

    public static native TemplateInstance cd(CDDTO cd);

    public static native TemplateInstance signin(String loginError, String passwordError, String login);
  }
}
