package repositories.impl;

import models.Event;
import repositories.EventsRepository;

import java.util.ArrayList;
import java.util.List;

public class EvensRepositoryListImpl implements EventsRepository {
    private final List<Event> events = new ArrayList<>();
    private int generatedID = 0;


    @Override
    public Event findByID(int id) {
        return events.stream()
                .filter(event->event.getId()==(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public void save(Event event) {
        events.add(event);
        event.setId(generatedID);
        generatedID++;
    }

    @Override
    public void deleteByID(int id) {
        events.remove(events.get(id));
    }

    @Override
    public Event findOneBySubstring(String subString) {
        return events.stream()
                .filter(event -> event.getTitle().contains(subString))
                .findFirst()
                .orElse(null);
    }
}
