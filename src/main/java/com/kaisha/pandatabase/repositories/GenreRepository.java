package com.kaisha.pandatabase.repositories;

import com.kaisha.pandatabase.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByGenreNameContainingIgnoreCase(String genrename);
}
