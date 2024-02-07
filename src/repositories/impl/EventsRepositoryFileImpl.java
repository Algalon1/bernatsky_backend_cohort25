package repositories.impl;

import models.Event;
import repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventsRepositoryFileImpl implements EventsRepository {
    private final String filename;
    private int generatedID = 0;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public EventsRepositoryFileImpl(String filename) {
        this.filename = filename;
    }
    @Override
    public Event findByID(int id) {
        return null;
    }
    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .map(parsed ->
                            new Event(
                                    Integer.parseInt(parsed[0]),
                                    parsed[1],
                                    LocalDate.parse(parsed[2], dateFormat),
                                    LocalDate.parse(parsed[3], dateFormat)))
                    .toList();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read the file");
        }
    }
    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            event.setId(generatedID);
            writer.append(String.format("%s|%s|%s|%s", event.getId(), event.getTitle(), event.getStartDate().format(dateFormat), event.getEndDate().format(dateFormat)));
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot write to the file");
        }
        generatedID++;
    }
    @Override
    public void deleteByID(int id) {
        List<Event> events = findAll();
        List<Event> newEventsList = events.stream().filter(event -> !(event.getId()==id)).toList();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Event event : newEventsList) {
                String formattedStartDate = event.getStartDate().format(dateFormat);
                String formattedEndDate = event.getEndDate().format(dateFormat);
                writer.write(String.format("%s|%s|%s|%s%n", event.getId(), event.getTitle(), formattedStartDate, formattedEndDate));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot write to the file");
        }
    }
    @Override
    public Event findOneBySubstring(String subString) {
        List<Event> events = findAll();
        return events.stream()
                .filter(event->event.getTitle().contains(subString))
                .findFirst().orElse(null);
    }
}
