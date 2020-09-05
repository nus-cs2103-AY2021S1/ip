package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event that has a date.
 * The date is denoted by a LocalDateTime object.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Creates a event task object with the given description and date.
     * @param description Description of the event.
     * @param at Date of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDateTime at, LocalDate doneDate) {
        super(description, doneDate);
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
