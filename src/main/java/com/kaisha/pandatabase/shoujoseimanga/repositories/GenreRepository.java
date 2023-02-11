package com.kaisha.pandatabase.shoujoseimanga.repositories;

import com.kaisha.pandatabase.shoujoseimanga.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByGenreNameContainingIgnoreCase(String genrename);
}
