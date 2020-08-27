package duke.task;

import duke.util.DateTimeHandler;
import duke.DukeException;

/**
 * Represents an event as a task with a date and time.
 */
public class Event extends Task {

    /** Date and time of the event. */
    private final String dateTime;

    /**
     * Creates a new event from a description and a date and time String.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     * @throws DukeException If the date and time input is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.dateTime = DateTimeHandler.parseDateTime(at);
    }

    /**
     * Creates a new event from a description, completion status and a date and time String.
     *
     * @param description Description of the event.
     * @param isDone Completion status of the event.
     * @param dateTime Date and time of the event.
     */
    public Event(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Gets the date and time of the event.
     *
     * @return Date and time of the event.
     */
    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String getData() {
        return "E / " + super.getData() + " / " + this.getDateTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
