package com.pluralsight.persistence.web;

import com.pluralsight.persistence.web.catalog.BookDTO;
import com.pluralsight.persistence.web.catalog.CDDTO;
import com.pluralsight.persistence.web.catalog.CatalogProxy;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.List;

public class WebApplication extends Controller {

  private static final Logger LOG = Logger.getLogger(WebApplication.class);

  @Inject
  @RestClient
  CatalogProxy catalogProxy;

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

  @CheckedTemplate
  static class Templates {
    public static native TemplateInstance index();
    public static native TemplateInstance books(List<BookDTO> books);
    public static native TemplateInstance book(BookDTO book);
    public static native TemplateInstance cds(List<CDDTO> cds);
    public static native TemplateInstance cd(CDDTO cd);
  }
}