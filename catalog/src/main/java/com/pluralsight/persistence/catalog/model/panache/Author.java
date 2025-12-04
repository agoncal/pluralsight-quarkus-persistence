package com.pluralsight.persistence.catalog.model.panache;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_authors")
public class Author extends Person {

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_language")
    public Language preferredLanguage;

    @URL
    @Size(max = 255)
    public String website;

    @ManyToMany(mappedBy = "authors")
    public List<Book> books = new ArrayList<>();
}
