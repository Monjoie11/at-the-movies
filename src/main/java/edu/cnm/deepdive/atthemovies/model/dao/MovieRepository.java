package edu.cnm.deepdive.atthemovies.model.dao;

import edu.cnm.deepdive.atthemovies.model.enity.Movie;
import edu.cnm.deepdive.atthemovies.model.enity.Movie.Genre;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepository extends CrudRepository<Movie, UUID> {

  List<Movie> getAllByOrderByTitleAsc();

  List<Movie> getAllByGenreOrderByTitleAsc(Genre genre);
}