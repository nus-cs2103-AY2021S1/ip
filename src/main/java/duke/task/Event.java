package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task with a type of Event.
 */
public class Event extends Task {
    /**
     * Represents the date and time of Event task.
     */
    private final LocalDateTime at;

    /**
     * Creates Event with user provided description, date and time.
     * @param description Description of Event.
     * @param at Date and time of Event task.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts Event to string form to be saved in user's files.
     * @return String format of Event task.
     */
    @Override
    public String taskToText() {
        return "E|" + super.completed + "|" + super.taskName + "|" + at;
    }

    /**
     * Returns string format of Event.
     * @return A string representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("E, d MMM yyyy, h.mm a")) + ")";
    }
}
