package controllers;

import models.Event;
import services.EventsService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EventsController {
    private final Scanner scanner;

    private final EventsService eventsService;

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public EventsController(Scanner scanner, EventsService eventsService) {
        this.scanner = scanner;
        this.eventsService = eventsService;
    }

    public void addEvent(){
        System.out.println("Enter the title");
        String title = scanner.nextLine();

        System.out.println("Enter start date (format: dd.MM.yyyy): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(),dateFormat);

        System.out.println("Enter end date (format: dd.MM.yyyy): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine(),dateFormat);

        Event event = eventsService.addEvent(title,startDate,endDate);

        System.out.println(event);
    }

    public void getAllEvents(){
        List<Event> events = eventsService.getAllEvents();
        System.out.println(events);
    }

    public void deleteEvent(){
        System.out.println("Enter the id of event to delete");
        int id = scanner.nextInt();
        eventsService.deleteEvent(id);
        System.out.println();
    }
}
