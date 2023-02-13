package com.kaisha.pandatabase.shoujoseimanga.controllers;

import com.kaisha.pandatabase.shoujoseimanga.models.Genre;
import com.kaisha.pandatabase.shoujoseimanga.models.Manga;
import com.kaisha.pandatabase.shoujoseimanga.repositories.GenreRepository;
import com.kaisha.pandatabase.shoujoseimanga.repositories.MangaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//@CrossOrigin(origins = "http://localhost:8888")
@RequestMapping("/api")
@RestController
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

    @PutMapping("genres/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable("id") long id, @RequestBody Genre genre) {
        Genre _genre = genreRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Genre not found with id = " + id));

        _genre.setGenreName(genre.getGenreName());

        return new ResponseEntity<>(genreRepository.save(_genre), HttpStatus.OK);
    }

    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre _genre = genreRepository.save(new Genre(genre.getGenreName()));

        return new ResponseEntity<>(_genre, HttpStatus.CREATED);
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<HttpStatus> deleteGenreById(@PathVariable("id") long id) {
        genreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/genres")
    public ResponseEntity<HttpStatus> deleteAllGenres() {
        genreRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
