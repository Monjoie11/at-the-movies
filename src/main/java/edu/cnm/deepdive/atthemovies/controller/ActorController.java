package edu.cnm.deepdive.atthemovies.controller;

import edu.cnm.deepdive.atthemovies.model.dao.ActorRepository;
import edu.cnm.deepdive.atthemovies.model.dao.MovieRepository;
import edu.cnm.deepdive.atthemovies.model.enity.Actor;
import edu.cnm.deepdive.atthemovies.model.enity.Movie;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("actors")
@ExposesResourceFor(Actor.class)
public class ActorController {

  private ActorRepository repository;
  private MovieRepository movieRepository;

  @Autowired
  public ActorController(ActorRepository repository,
      MovieRepository movieRepository){
    this.repository = repository;
    this.movieRepository = movieRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Actor> list(){
    return repository.getAllByOrderByName();
  }

  @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Actor> search(@RequestParam(value = "q", required = true) String nameFragment){
    return repository.getAllByNameContainsOrderByNameAsc(nameFragment);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Actor> post(@RequestBody Actor actor){
    repository.save(actor);
    return ResponseEntity.created(actor.getHref()).body(actor);
  }
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Actor get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }


  @PutMapping(value = "{actorId}/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Actor attach(@PathVariable("actorId") UUID actordId, @PathVariable("movieId") UUID movieId){
    Actor actor = get(actordId);
    Movie movie = movieRepository.findById(movieId).get();
    if(!actor.getMovies().contains(movie)){
      actor.getMovies().add(movie);
    }
    return repository.save(actor);
  }

  @DeleteMapping(value = "{actorId}/movies/{movieId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void detach(@PathVariable("actorId") UUID actordId, @PathVariable("movieId") UUID movieId){
    Actor actor = get(actordId);
    Movie movie = movieRepository.findById(movieId).get();
    actor.getMovies().remove(movie);
      repository.save(actor);
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
