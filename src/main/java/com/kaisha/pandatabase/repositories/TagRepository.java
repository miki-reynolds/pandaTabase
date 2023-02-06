package com.kaisha.pandatabase.repositories;

import com.kaisha.pandatabase.models.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findTagsByMangasId(Long mangaId);
}
