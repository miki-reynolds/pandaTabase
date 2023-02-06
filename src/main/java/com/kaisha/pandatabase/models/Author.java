package com.kaisha.pandatabase.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String authorName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Manga> mangas = new HashSet<>();

    public Author() {
    }

    public Author(String authorName) {
        super();
        this.authorName = authorName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }
}
