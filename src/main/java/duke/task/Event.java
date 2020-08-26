package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is an extension of a task that has a date.
 * The date is denoted by a LocalDateTime object.
 */
public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toData() {
        return "E | " + super.toData() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + "h)";
    }
}