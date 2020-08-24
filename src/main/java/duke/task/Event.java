package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task that inherits from Task class, and has an additional condition, which is when it is held at.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates a new Event object
     *
     * @param description details about the Event
     * @param at time/date the event is held at
     * @return Event with a corresponding description and completed status.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at.trim());
    }

    /**
     * Creates a new Event object
     *
     * @param description details about the Event
     * @param isDone whether Event is done or not
     * @param at time/date the event is held at
     * @return Event with a corresponding description and completed status.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDate.parse(at.trim());
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the event
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    @Override
    public String infoString() {
        String x = "0";
        if (isDone) {
            x = "1";
        }
        return "E | " + x +  " | " + this.description + " | " + at;
    }
}
