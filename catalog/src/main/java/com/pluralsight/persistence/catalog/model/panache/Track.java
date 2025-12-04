package com.pluralsight.persistence.catalog.model.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Duration;

@Entity
@Table(name = "t_tracks")
public class Track extends PanacheEntity {

    @NotNull
    @Size(max = 200)
    @Column(length = 200, nullable = false)
    public String title;

    @NotNull
    @Column(nullable = false)
    public Duration duration;

    @NotNull
    @Min(1)
    @Column(name = "track_number", nullable = false)
    public Integer trackNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cd_fk", nullable = false)
    public CD cd;
}
