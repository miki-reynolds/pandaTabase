package com.kaisha.pandatabase;

import com.kaisha.pandatabase.models.Author;
import com.kaisha.pandatabase.models.Genre;
import com.kaisha.pandatabase.models.Manga;
import com.kaisha.pandatabase.repositories.AuthorRepository;
import com.kaisha.pandatabase.repositories.GenreRepository;
import com.kaisha.pandatabase.repositories.MangaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


@SpringBootApplication
public class PandatabaseApplication {
    private final static Logger logger = LoggerFactory.getLogger(PandatabaseApplication.class);

    @Autowired
    private MangaRepository mangaRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(PandatabaseApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(MangaRepository mangaRepository) {
        return args -> {
            logger.info("INSIDE THE APP CONTEXT!!");
            Genre shoujo = new Genre("shoujo");
            genreRepository.save(shoujo);

            Author author = new Author("Miki");
            authorRepository.save(author);

            Manga m1 = new Manga("Sensei Kunshuu", shoujo, author, 2010);
            Manga m2 = new Manga("Mens Life", shoujo, author, 2011);
            mangaRepository.saveAll(Arrays.asList(m1, m2));
            for (Manga manga : mangaRepository.findAll()) {
                logger.info(manga.getTitle() + " right here!!");
            }
        };
    }
}