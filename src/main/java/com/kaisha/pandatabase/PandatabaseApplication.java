package com.kaisha.pandatabase;

import com.kaisha.pandatabase.models.Author;
import com.kaisha.pandatabase.models.Genre;
import com.kaisha.pandatabase.models.Manga;
import com.kaisha.pandatabase.models.Tag;
import com.kaisha.pandatabase.repositories.AuthorRepository;
import com.kaisha.pandatabase.repositories.GenreRepository;
import com.kaisha.pandatabase.repositories.MangaRepository;
import com.kaisha.pandatabase.repositories.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class PandatabaseApplication {
    private final static Logger logger = LoggerFactory.getLogger(PandatabaseApplication.class);

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private TagRepository tagRepository;

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
            Tag sensei = new Tag("sensei");
            Tag senpai = new Tag("senpai");
            Tag genderBender = new Tag("gender bender");
            Tag medical = new Tag("medical");
            tagRepository.saveAll(Arrays.asList(sensei, senpai, genderBender, medical));

            Genre shoujo = new Genre("shoujo");
            Genre josei = new Genre("josei");
            genreRepository.saveAll(Arrays.asList(shoujo, josei));

            Author miki = new Author("Miki");
            Author reynolds = new Author("Reynolds");
            authorRepository.saveAll(Arrays.asList(miki, reynolds));

            Manga m1 = new Manga("Seiyuu Academy", shoujo, miki, 2010);
            Manga m2 = new Manga("Sensei Kunshuu", shoujo, miki, 2011);
            Manga m3 = new Manga("Mens Life", shoujo, reynolds, 2019);
            Manga m4 = new Manga("Koi wa Tsuzuku", josei, reynolds, 2020);
            m1.addTag(genderBender);
            m2.addTag(sensei);
            m3.addTag(senpai);
            m3.addTag(genderBender);
            m3.addTag(sensei);
            m3.addTag(medical);
            logger.info(m1.getTags().toString() + "all m1 tags!!!");
            logger.info(m2.getTags().toString() + "all m2 tags!!!");

            mangaRepository.saveAll(Arrays.asList(m1, m2, m3, m4));
            logger.info(mangaRepository.findAll().toString());
            for (Manga manga : mangaRepository.findAll()) {
                logger.info(manga.getTitle() + " right here!!");
            }
        };
    }
}