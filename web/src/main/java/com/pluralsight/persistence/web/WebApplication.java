package com.pluralsight.persistence.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.Path;
import org.jboss.logging.Logger;

public class WebApplication /*TODO extends Controller*/ {

  private static final Logger LOG = Logger.getLogger(WebApplication.class);

  @Path("/")
  public TemplateInstance index() {
    LOG.info("Entering index()");
    return Templates.index();
  }

  @CheckedTemplate
  static class Templates {
    public static native TemplateInstance index();
  }
}