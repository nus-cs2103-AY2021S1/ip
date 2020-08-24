package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done at a certain date and time. An
 * <code>Event</code> object is represented as a Task with a LocalDateTime value.
 */
public class Event extends Task {
    /** Indicates when the Event is held at */
    protected LocalDateTime at;

    /**
     * Constructs an <code>Event</code> object with a description and a LocalDateTime value.
     * This Event is marked as undone.
     *
     * @param description Describes what to do.
     * @param at String representation of date and time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Constructs an <code>Event</code> object with a description,
     * a boolean to indicate if the Event is done and a LocalDateTime value.
     *
     * @param description Describes what to do.
     * @param isDone Indicates if the Event is done.
     * @param at String representation of date and time.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns the LocalDateTime in which the Event will take place at.
     *
     * @return A LocalDateTime object.
     */
    public LocalDateTime getDateTime() {
        return this.at;
    }

    /**
     * Returns a description of the Event to be stored in a file.
     *
     * @return A description of the Event.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | "
                + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns a description of the Event.
     *
     * @return A description of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a")) + ")";
    }
}
