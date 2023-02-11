package com.kaisha.pandatabase.shoujoseimanga.repositories;

import com.kaisha.pandatabase.shoujoseimanga.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByTagNameContainingIgnoreCase(String tagName);
}
