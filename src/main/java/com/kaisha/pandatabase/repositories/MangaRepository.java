package com.kaisha.pandatabase.repositories;

import com.kaisha.pandatabase.models.Author;
import com.kaisha.pandatabase.models.Genre;
import com.kaisha.pandatabase.models.Manga;
import com.kaisha.pandatabase.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//@RepositoryRestResource(path="shoujoseimangas") to customize the pathname in rest api
public interface MangaRepository extends JpaRepository<Manga, Long> {
    List<Manga> findMangasByTitleContainingIgnoreCase(String title);
    List<Manga> findMangasByPublishYearOrderByTitle(int year);
    List<Manga> findMangasByPublishYearIsLessThanEqualOrderByPublishYearDesc(int year);
    List<Manga> findMangasByPublishYearIsGreaterThanEqualOrderByPublishYearDesc(int year);
    List<Manga> findMangasByAuthor(Author author);
    List<Manga> findMangasByGenre(Genre genre);
    List<Manga> findMangasByTagsContaining(Tag tag);

}
