package com.kaisha.pandatabase.repositories;

import com.kaisha.pandatabase.models.Manga;
import org.springframework.data.repository.CrudRepository;


public interface MangaRepository extends CrudRepository<Manga, Long> {
}
