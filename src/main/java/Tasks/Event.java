package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Create Event objects extends from Task class.
 */
public class Event extends Task {
    /** Event's date. */
    protected LocalDate at;

    /**
     * Constructs Event object with event's description and event's date given.
     *
     * @param description Event's description.
     * @param at Event's date.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs Event object with description event status and event date given.
     *
     * @param description Event's description.
     * @param at Event's date.
     * @param isDone Event's status.
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Create a new Event object with isDone equal true.
     *
     * @return New Event object.
     */
    @Override
    protected Event markAsDone() {
        return new Event(super.description, this.at, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "[" + at.getDayOfWeek() + "])";
    }
}

