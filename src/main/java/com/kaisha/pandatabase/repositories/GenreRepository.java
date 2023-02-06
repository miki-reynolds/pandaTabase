package com.kaisha.pandatabase.repositories;

import com.kaisha.pandatabase.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
