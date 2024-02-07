package models;

import java.time.LocalDate;

public class Event {
    private Long generatedID = 1L;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(Long generatedID, String title, LocalDate startDate, LocalDate endDate) {
        this.generatedID = generatedID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event() {
    }

    public Event(String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
