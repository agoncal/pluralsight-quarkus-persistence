package com.pluralsight.persistence.catalog.model.panache;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_musicians")
public class Musician extends Person {

    @Size(max = 100)
    @Column(name = "stage_name", length = 100)
    public String stageName;

    @Size(max = 50)
    @Column(length = 50)
    public String instrument;

    @ManyToMany(mappedBy = "musicians")
    public List<CD> cds = new ArrayList<>();
}
