package com.pluralsight.persistence.catalog.rest;

import com.pluralsight.persistence.catalog.model.CD;
import com.pluralsight.persistence.catalog.model.MusicGenre;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.Hibernate;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/cds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CDResource {

  private static final Logger LOG = Logger.getLogger(CDResource.class);

  @GET
  public List<CD> getAllCDs(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("10") int size,
      @QueryParam("inStock") Boolean inStock,
      @QueryParam("genre") MusicGenre genre,
      @QueryParam("label") String label,
      @QueryParam("sortBy") @DefaultValue("title") String sortBy,
      @QueryParam("sortDir") @DefaultValue("asc") String sortDir) {
    LOG.info("Entering getAllCDs() with page=" + page + ", size=" + size + ", inStock=" + inStock + ", genre=" + genre + ", label=" + label + ", sortBy=" + sortBy + ", sortDir=" + sortDir);

    // Build sort
    Sort sort = Sort.by(sortBy);
    if ("desc".equalsIgnoreCase(sortDir)) {
      sort = sort.descending();
    }

    // Build query dynamically
    StringBuilder query = new StringBuilder();
    Map<String, Object> params = new HashMap<>();
    List<String> conditions = new ArrayList<>();

    if (inStock != null) {
      if (inStock) {
        conditions.add("stock > 0");
      } else {
        conditions.add("stock = 0");
      }
    }

    if (genre != null) {
      conditions.add("genre = :genre");
      params.put("genre", genre);
    }

    if (label != null && !label.isBlank()) {
      conditions.add("lower(musicCompany) like :label");
      params.put("label", "%" + label.toLowerCase() + "%");
    }

    if (conditions.isEmpty()) {
      return CD.findAll(sort).page(Page.of(page, size)).list();
    }

    query.append(String.join(" and ", conditions));
    return CD.find(query.toString(), sort, params).page(Page.of(page, size)).list();
  }

  @GET
  @Path("/{id}")
  @Transactional
  @CacheResult(cacheName = "cd-cache")
  public Response getCD(@CacheKey @PathParam("id") Long id) {
    LOG.info("Entering getCD() with id: " + id);
    CD cd = CD.findById(id);
    if (cd == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    Hibernate.initialize(cd.musicians);
    Hibernate.initialize(cd.tracks);
    return Response.ok(cd).build();
  }

  @POST
  @Transactional
  public Response createCD(@Valid CD cd) {
    LOG.info("Entering createCD() with title: " + cd.title);
    cd.persist();
    return Response.created(URI.create("/api/cds/" + cd.id)).entity(cd).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response updateCD(@PathParam("id") Long id, @Valid CD cd) {
    LOG.info("Entering updateCD() with id: " + id);
    CD existingCD = CD.findById(id);
    if (existingCD == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    existingCD.title = cd.title;
    existingCD.description = cd.description;
    existingCD.price = cd.price;
    existingCD.stock = cd.stock;
    existingCD.ean = cd.ean;
    existingCD.musicCompany = cd.musicCompany;
    existingCD.genre = cd.genre;
    existingCD.totalDuration = cd.totalDuration;
    existingCD.releaseDate = cd.releaseDate;
    Hibernate.initialize(existingCD.musicians);
    Hibernate.initialize(existingCD.tracks);
    return Response.ok(existingCD).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deleteCD(@PathParam("id") Long id) {
    LOG.info("Entering deleteCD() with id: " + id);
    CD cd = CD.findById(id);
    if (cd == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    cd.delete();
    return Response.noContent().build();
  }
}
