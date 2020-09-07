package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    /**
     * The date of the event.
     */
    private final LocalDateTime at;

    /**
     * Initializes an event object.
     *
     * @param description The description of the event.
     * @param at The date of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public LocalDateTime getDate() {
        return at;
    }

    @Override
    public String toString() {
        String date = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
