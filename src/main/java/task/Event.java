package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task
 */
public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at, boolean done) {
        super(description, done);
        this.at = at;
    }

    /**
     * Returns the event time of the task.
     *
     * @return Date and time of the event.
     */
    public LocalDateTime getTiming() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }
}