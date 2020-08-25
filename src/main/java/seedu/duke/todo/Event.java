package main.java.seedu.duke.todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event happening at a particular time.
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the date of the event.
     * @return the date of the event.
     */
    @Override
    public LocalDate getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the data representation of the event.
     * @return the data representation of the event.
     */
    @Override
    public String getData() {
        return "E " + super.getData() + " | " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
