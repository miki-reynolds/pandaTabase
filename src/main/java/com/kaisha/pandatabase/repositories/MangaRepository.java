package com.kaisha.pandatabase.repositories;

import com.kaisha.pandatabase.models.Manga;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


//@RepositoryRestResource(path="shoujoseimangas") to customize the pathname in rest api
@RepositoryRestResource
public interface MangaRepository extends CrudRepository<Manga, Long> {
    List<Manga> findMangasByTagsName(Long tagId);
//    List<Manga> findByGenresGenreName(String genreName);

}
