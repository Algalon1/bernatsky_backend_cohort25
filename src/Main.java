import controllers.EventsController;
import repositories.EventsRepository;
import repositories.impl.EvensRepositoryListImpl;
import repositories.impl.EventsRepositoryFileImpl;
import services.EventsService;
import services.impl.EventsServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventsRepository eventsRepositoryList = new EvensRepositoryListImpl();
        EventsRepository eventsRepositoryFile = new EventsRepositoryFileImpl("src/events.txt");
        EventsService eventsService = new EventsServiceImpl(eventsRepositoryFile);
        EventsController eventsController = new EventsController(scanner,eventsService);
        boolean isRun = true;
        while (isRun) {
            String command = scanner.nextLine();
            switch (command) {
                case "/addEvent" -> eventsController.addEvent();
                case "/events" -> eventsController.getAllEvents();
                case "/delete" -> eventsController.deleteEvent();
                case "/find" -> eventsController.findBySubstring();
                case "/exit" ->  isRun = false;
            }
        }
    }
}
