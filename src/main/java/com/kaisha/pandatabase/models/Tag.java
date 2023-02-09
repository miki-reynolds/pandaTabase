package com.kaisha.pandatabase.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "tags")
@JsonIgnoreProperties({"hypernateLazyInitializer", "handler"})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tagName;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "tags")
    private Set<Manga> mangas = new HashSet<>();;

    public Tag() {
    }

    public Tag(String tagname) {
        super();
        this.tagName = tagname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void addManga(Manga manga) {
        this.mangas.add(manga);
        manga.getTags().add(this);
    }

    public void removeManga(Long mangaId) {
        Manga manga = this.mangas.stream().filter(m -> m.getId() == mangaId).findFirst().orElse(null);
        if (manga != null) {
            mangas.remove(manga);
            manga.getTags().remove(this);
        }
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Tag compTag)) {
            return false;
        }

        return this.tagName.equalsIgnoreCase(compTag.tagName);
    }
}
