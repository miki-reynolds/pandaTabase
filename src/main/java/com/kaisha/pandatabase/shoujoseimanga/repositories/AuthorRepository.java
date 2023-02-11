package com.kaisha.pandatabase.shoujoseimanga.repositories;

import com.kaisha.pandatabase.shoujoseimanga.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByAuthorNameContainingIgnoreCase(String authorName);

}
