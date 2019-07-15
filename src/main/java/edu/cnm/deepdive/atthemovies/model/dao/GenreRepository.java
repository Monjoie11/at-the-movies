package edu.cnm.deepdive.atthemovies.model.dao;

import edu.cnm.deepdive.atthemovies.model.enity.Genre;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, UUID> {

  List<Genre> getAllByOrderByNameAsc();
}
