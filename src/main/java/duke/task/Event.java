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
     *
     * @param description The description of the event.
     * @param at The date at which the event occurs.
     */
    public Event(String description, LocalDate at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    /**
     * Returns true since events have dates associated to them.
     *
     * @return True, since events have dates associated to them.
     */
    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * Gets the date at which this task occurs.
     *
     * @return The date at which this task occurs.
     */
    @Override
    public LocalDate getDate() {
        return this.at;
    }

    /**
     * Returns a string representation of this event.
     *
     * @return String representation of this event.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)",
                super.toString(),
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
