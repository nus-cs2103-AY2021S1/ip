package juke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that occurs at a given event date.
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Constructs an Event Task with an input description and at Date.
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns the representative text of the juke.task.Event.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "E";
        return String.format("%s/%s/%s",
                type,
                super.taskSaver(),
                at.format(DateTimeFormatter.ISO_LOCAL_DATE));
        // return type + "/" + super.taskSaver() + "/" + at.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Outputs the Event as a String.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
