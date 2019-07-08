package edu.cnm.deepdive.atthemovies.view;

import edu.cnm.deepdive.atthemovies.model.enity.Actor;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

public interface FlatActor {

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getName();

  URI getHref();


}
