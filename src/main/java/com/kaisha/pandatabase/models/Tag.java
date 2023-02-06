package com.kaisha.pandatabase.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tagname;

    @ManyToMany(mappedBy = "tags")
    private Set<Manga> mangas = new HashSet<>();;

    public Tag() {
    }

    public Tag(String tagname) {
        super();
        this.tagname = tagname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }
}
