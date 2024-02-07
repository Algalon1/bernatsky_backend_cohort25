package services.impl;

import models.Event;
import repositories.EventsRepository;
import services.EventsService;

import java.time.LocalDate;
import java.util.List;

public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Event addEvent(String title, LocalDate startDate, LocalDate endDate) {
        if(title == null || title.isEmpty() || title.equals(" ")){
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if(startDate == null){
            throw new IllegalArgumentException("Start date cannot be empty");
        }
        if(endDate == null){
            throw new IllegalArgumentException("End date cannot be empty");
        }

        if (endDate.isBefore(startDate)){
            throw new IllegalArgumentException("End date cannot be earlier, than the start date");
        }

        Event event = new Event(title,startDate,endDate);

        eventsRepository.save(event);

        return event;
    }

    public void deleteEvent(int id){
        if(containsEventWithId(id)){
            eventsRepository.deleteByID(id);
        }else {
            System.out.println("Сannot delete event with provided ID");
        }

    }
    private boolean containsEventWithId(int id) {
        for (Event event : eventsRepository.findAll()) {
            if (event.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }
}
