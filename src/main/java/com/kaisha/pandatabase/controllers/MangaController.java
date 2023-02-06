package com.kaisha.pandatabase.controllers;

import com.kaisha.pandatabase.models.Genre;
import com.kaisha.pandatabase.models.Manga;
import com.kaisha.pandatabase.models.Tag;
import com.kaisha.pandatabase.repositories.GenreRepository;
import com.kaisha.pandatabase.repositories.MangaRepository;
import com.kaisha.pandatabase.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;
    @Autowired
    private TagRepository tagRepository;
    //    public String getMangas() throws JsonProcessingException {
    //    ObjectMapper objectMapper = new ObjectMapper();
    //    String mangaList = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mangaRepository.findAll());
    //    return mangaList;
    //    }
    @GetMapping("/mangas")
    public Iterable<Manga> getMangas() {
        return mangaRepository.findAll();
    }

}
