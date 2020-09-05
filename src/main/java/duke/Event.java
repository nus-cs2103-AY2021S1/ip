package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs an Event task with description and date.
     * @param description Description of Event task.
     * @param at Date of event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an Event task with description, status and date.
     * @param description Description of Event task.
     * @param isDone Status of task.
     * @param at Date of event.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns date of event.
     * @return String representation of date of event.
     */
    public String getDate() {
        return at.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
