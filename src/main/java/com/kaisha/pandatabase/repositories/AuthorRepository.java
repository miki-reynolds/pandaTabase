package com.kaisha.pandatabase.repositories;

import com.kaisha.pandatabase.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
