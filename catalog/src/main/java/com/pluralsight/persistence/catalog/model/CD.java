package com.pluralsight.persistence.catalog.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Entity
@DiscriminatorValue("CD")
@NamedQuery(name = "CD.findByIdWithMusicians",
  query = "SELECT DISTINCT c FROM CD c LEFT JOIN FETCH c.musicians WHERE c.id = :id")
public class CD extends Item {

  public static Optional<CD> findByIdWithRelations(Long id) {
    // Fetch musicians via join, tracks via batch fetching (configured in application.properties)
    Optional<CD> cd = find("#CD.findByIdWithMusicians", Map.of("id", id)).firstResultOptional();
    cd.ifPresent(c -> c.tracks.size()); // Initialize tracks collection via batch fetch
    return cd;
  }

  @Size(min = 13, max = 13)
  @Column(length = 13, unique = true)
  public String ean;

  @Size(max = 100)
  @Column(name = "music_company", length = 100)
  public String musicCompany;

  @Enumerated(EnumType.STRING)
  public MusicGenre genre;

  @Column(name = "total_duration")
  public Duration totalDuration;

  @Column(name = "release_date")
  public LocalDate releaseDate;

  @ManyToMany
  @JoinTable(
    name = "t_cd_musicians",
    joinColumns = @JoinColumn(name = "cd_id"),
    inverseJoinColumns = @JoinColumn(name = "musician_id")
  )
  public List<Musician> musicians = new ArrayList<>();

  @OneToMany(mappedBy = "cd", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("trackNumber")
  public List<Track> tracks = new ArrayList<>();
}
