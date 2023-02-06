package com.kaisha.pandatabase.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String genreName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private Set<Manga> mangas = new HashSet<>();

    public Genre() {
    }

    public Genre(String genreName) {
        super();
        this.genreName = genreName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }
}
