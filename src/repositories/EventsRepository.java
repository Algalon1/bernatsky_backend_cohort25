package repositories;

import models.Event;

public interface EventsRepository extends CrudRepository<Event>{

    Event findOneBySubstring(String subString);
}
