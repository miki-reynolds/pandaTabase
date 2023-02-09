package com.kaisha.pandatabase.controllers;

import com.kaisha.pandatabase.models.Manga;
import com.kaisha.pandatabase.models.Tag;
import com.kaisha.pandatabase.repositories.MangaRepository;
import com.kaisha.pandatabase.repositories.TagRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8888")
@RestController
//@RequestMapping("/api")
public class TagController {
    private final TagRepository tagRepository;
    private final MangaRepository mangaRepository;

    public TagController(TagRepository tagRepository, MangaRepository mangaRepository) {
        this.tagRepository = tagRepository;
        this.mangaRepository = mangaRepository;
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags(@RequestParam(required = false) String tagName) {
        List<Tag> tags = new ArrayList<>();

        if (tagName == null) {
            tags.addAll(tagRepository.findAll());
        } else {
            tags.addAll(tagRepository.findByTagNameContainingIgnoreCase(tagName));
        }

        return tags.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<List<Manga>> getAllMangasByTagId(@PathVariable(value = "id") long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Tag not found with id = " + id));

        List<Manga> mangas = mangaRepository.findMangasByTagsContaining(tag);

        return mangas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(mangas, HttpStatus.OK);
    }
}
