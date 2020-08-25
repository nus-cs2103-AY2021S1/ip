package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        LocalDate d = LocalDate.parse(at);
        String reformattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyy"));
        return "[E]" + super.toString() + " (at: " + reformattedDate + ")";
    }
}
