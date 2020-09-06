package juke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An juke.task.Event class that represents a juke.task.Task that occurs on a day.
 * It has a juke.task.Task Description, as well as a juke.task.Event date.
 */
public class Event extends Task {

    private LocalDate at;

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
        return type + "/" + super.taskSaver() + "/" + at.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Outputs the juke.task.Event as a String.
     * @return String representation of juke.task.Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
