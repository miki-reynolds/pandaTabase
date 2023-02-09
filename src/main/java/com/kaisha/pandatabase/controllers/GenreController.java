package com.kaisha.pandatabase.controllers;

import com.kaisha.pandatabase.models.Genre;
import com.kaisha.pandatabase.models.Manga;
import com.kaisha.pandatabase.repositories.GenreRepository;
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
public class GenreController {
    private final GenreRepository genreRepository;
    private final MangaRepository mangaRepository;

    public GenreController(GenreRepository genreRepository, MangaRepository mangaRepository) {
        this.genreRepository = genreRepository;
        this.mangaRepository = mangaRepository;
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getAllGenres(@RequestParam(required = false) String genreName) {
        List<Genre> genres = new ArrayList<>();

        if (genreName == null) {
            genres.addAll(genreRepository.findAll());
        } else {
            genres.addAll(genreRepository.findByGenreNameContainingIgnoreCase(genreName));
        }

        return genres.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<List<Manga>> getAllMangasByGenreId(@PathVariable(value = "id") long id) {
        Genre genreQ = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id = " + id));
        List<Manga> mangas = mangaRepository.findMangasByGenre(genreQ);

        return mangas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(mangas, HttpStatus.OK);
    }
}
