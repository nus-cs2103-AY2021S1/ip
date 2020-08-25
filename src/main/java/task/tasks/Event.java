package task.tasks;

import datetimeconverter.DateTimeConverter;

/**
 * Task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /**
     * Start and end time of event.
     */
    protected String at;

    /**
     * Creates an event.
     *
     * @param description Contents of event.
     * @param at          Start and end time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of a event description and its completion status.
     *
     * @return String representation of a event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeConverter.formatDateTime(at) + ")";
    }
}
