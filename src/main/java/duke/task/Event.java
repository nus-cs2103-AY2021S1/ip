package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that occur at a specific time.
 */
public class Event extends Task {
    /** The date at which this task occurs. */
    protected LocalDate at;

    /**
     * Creates a new Event with the specified description and specified event date.
     * @param description The description of the event.
     * @param at The date at which the event occurs.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the date at which this task occurs.
     * @return  The date at which this task occurs.
     */
    public LocalDate getAt() {
        return this.at;
    }

    /**
     * Gets the short form of this event.
     * @return The short form of this event.
     */
    @Override
    public String getShortForm() {
        return "E";
    }

    /**
     * Returns a string representation of this event.
     * @return A string representation of this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
