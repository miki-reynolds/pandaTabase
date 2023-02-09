package com.kaisha.pandatabase.controllers;

import com.kaisha.pandatabase.models.Author;
import com.kaisha.pandatabase.models.Manga;
import com.kaisha.pandatabase.repositories.AuthorRepository;
import com.kaisha.pandatabase.repositories.MangaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8888")

@RestController
//@RequestMapping("/api")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final MangaRepository mangaRepository;

    public AuthorController(AuthorRepository authorRepository, MangaRepository mangaRepository) {
        this.authorRepository = authorRepository;
        this.mangaRepository = mangaRepository;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(required = false) String authorName) {
        List<Author> authors = new ArrayList<>();

        if (authorName == null) {
            authors.addAll(authorRepository.findAll());
        } else {
            authors.addAll(authorRepository.findAuthorsByAuthorNameContainingIgnoreCase(authorName));
        }

        return authors.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<List<Manga>> getAllMangasByAuthorId(@PathVariable(value = "id") long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found with id = " + id));
        List<Manga> mangas = mangaRepository.findMangasByAuthor(author);

        return mangas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(mangas, HttpStatus.OK);
    }
}
