package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Storage;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description, "E");
        this.date = date;
    }

    public Event(String description, Priority p, LocalDate date) {
        super(description, p, "E");
        this.date = date;
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat() + Storage.LINE + date;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" (at: %s)", date.format(
                    DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
