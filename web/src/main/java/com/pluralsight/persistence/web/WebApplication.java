package com.pluralsight.persistence.web;

import com.pluralsight.persistence.web.catalog.BookDTO;
import com.pluralsight.persistence.web.catalog.CDDTO;
import com.pluralsight.persistence.web.catalog.CatalogProxy;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
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

  @Path("/cds")
  public TemplateInstance cds() {
    LOG.info("Entering cds()");
    List<CDDTO> cds = catalogProxy.getAllCDs();
    return Templates.cds(cds);
  }

  @CheckedTemplate
  static class Templates {
    public static native TemplateInstance index();
    public static native TemplateInstance books(List<BookDTO> books);
    public static native TemplateInstance cds(List<CDDTO> cds);
  }
}