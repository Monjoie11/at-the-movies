package edu.cnm.deepdive.atthemovies.view;

import edu.cnm.deepdive.atthemovies.model.enity.Actor;
import edu.cnm.deepdive.atthemovies.model.enity.Movie;
import edu.cnm.deepdive.atthemovies.model.enity.Movie.Genre;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface FlatMovie {


   UUID getId() ;

   Date getCreated() ;

   Date getUpdated();

   String getTitle() ;

  String getScreenwriter() ;

  Genre getGenre() ;

  URI getHref();
}
