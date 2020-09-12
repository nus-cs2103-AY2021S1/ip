package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of <code>Task</code>, where a date must be specified.
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates an Event with the specified <code>description</code> and
     * <code>at</code>.
     *
     * @param description Description of the task.
     * @param at Date at which this event should occur.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        LocalDate d = LocalDate.parse(at);
        String reformattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyy"));
        return "[E]" + super.toString() + " (at: " + reformattedDate + ")";
    }
}
