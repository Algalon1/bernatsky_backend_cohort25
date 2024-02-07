import controllers.EventsController;
import repositories.EventsRepository;
import repositories.impl.EvensRepositoryListImpl;
import services.EventsService;
import services.impl.EventsServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventsRepository eventsRepository = new EvensRepositoryListImpl();
        EventsService eventsService = new EventsServiceImpl(eventsRepository);
        EventsController eventsController = new EventsController(scanner,eventsService);
        boolean isRun = true;
        while (isRun) {
            String command = scanner.nextLine();
            switch (command) {
                case "/addEvent" -> eventsController.addEvent();
                case "/events" -> eventsController.getAllEvents();
                case "/delete" -> eventsController.deleteEvent();
                case "/exit" ->  isRun = false;

            }
        }
    }
}
