package com.kaisha.pandatabase.controllers;

import com.kaisha.pandatabase.models.Manga;
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
public class MangaController {
    private final MangaRepository mangaRepository;

    public MangaController(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    @GetMapping("/mangas")
    public ResponseEntity<List<Manga>> getAllMangas(@RequestParam(required = false) String title) {
        List<Manga> mangas = new ArrayList<>();

        if (title == null) {
            mangas.addAll(mangaRepository.findAll());
        } else {
            mangas.addAll(mangaRepository.findMangasByTitleContainingIgnoreCase(title));
        }

        return mangas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @GetMapping("/mangas/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable("id") long id) {
        Manga manga = mangaRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Manga not found with id = " + id));

        return new ResponseEntity<>(manga, HttpStatus.OK);
    }

    @GetMapping("/mangas/years/{year}")
    public ResponseEntity<List<Manga>> getMangasByPublishYear(@PathVariable("year") int year) {
        List<Manga> mangas = mangaRepository.findMangasByPublishYearOrderByTitle(year);

        return mangas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @GetMapping("/mangas/years/pre-{year}")
    public ResponseEntity<List<Manga>> getMangasByPublishYearLessThan(@PathVariable("year") int year) {
        List<Manga> mangas = mangaRepository.findMangasByPublishYearIsLessThanEqualOrderByPublishYearDesc(year);

        return mangas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(mangas, HttpStatus.OK);
    }
    @GetMapping("/mangas/years/post-{year}")
    public ResponseEntity<List<Manga>> getMangasByPublishYearGreaterThan(@PathVariable("year") int year) {
        List<Manga> mangas = mangaRepository.findMangasByPublishYearIsGreaterThanEqualOrderByPublishYearDesc(year);

        return mangas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(mangas, HttpStatus.OK);
    }


}
