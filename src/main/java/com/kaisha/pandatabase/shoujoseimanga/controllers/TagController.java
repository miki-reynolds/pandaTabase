package com.kaisha.pandatabase.shoujoseimanga.controllers;

import com.kaisha.pandatabase.shoujoseimanga.models.Manga;
import com.kaisha.pandatabase.shoujoseimanga.models.Tag;
import com.kaisha.pandatabase.shoujoseimanga.repositories.MangaRepository;
import com.kaisha.pandatabase.shoujoseimanga.repositories.TagRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//@CrossOrigin(origins = "http://localhost:8888")
//@RequestMapping("/api")
@RestController
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

    @PutMapping("tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tag) {
        Tag _tag = tagRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Tag not found with id = " + id));

        _tag.setTagName(tag.getTagName());

        return new ResponseEntity<>(tagRepository.save(_tag), HttpStatus.OK);
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag _tag = tagRepository.save(new Tag(tag.getTagName()));

        return new ResponseEntity<>(_tag, HttpStatus.CREATED);
    }

    @PostMapping("/tags/{mangaId}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable("mangaId") long mangaId, @RequestBody Tag tagRequest) {
        Tag tag = mangaRepository.findById(mangaId)
                .map(manga -> {
                    long tagId = tagRequest.getId();
                    // tag already exists
                    if (tagId != 0L) {
                        Tag _tag = tagRepository.findById(tagId).orElseThrow(()
                                -> new ResourceNotFoundException("Tag not found with id = " + tagId));
                        manga.addTag(_tag);
                        mangaRepository.save(manga);
                        return _tag;
                    }
                    // else create a new tag
                    manga.addTag(tagRequest);
                    mangaRepository.save(manga);
                    return tagRepository.save(tagRequest);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Manga not found with id = " + mangaId));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> deleteTagById(@PathVariable("id") long id) {
        mangaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tags")
    public ResponseEntity<HttpStatus> deleteAllTags() {
        tagRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
