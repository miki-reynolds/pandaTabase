package com.kaisha.pandatabase.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    private int publishYear;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "manga_tag",
            joinColumns = {@JoinColumn(name = "manga_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private Set<Tag> tags = new HashSet<>();

    public Manga() {
    }

    public Manga(String title, Genre genre, Author author, int publishYear) {
        super();
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.publishYear = publishYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

}
