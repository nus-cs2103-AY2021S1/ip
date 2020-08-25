package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A kind of tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    /**
     * Default constructor for Event.
     * @param description the description of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            atDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            atDate = null;
        }
    }

    /**
     * Construct a Event task assigned the done status.
     * @param isDone the done status of the task.
     * @param description the description of the task.
     */
    public Event(boolean isDone, String description, String at) {
        this(description, at);
        this.isDone = isDone;
    }

    /**
     * Return the String to be store in files.
     * @return the String to be store in files.
     */
    @Override
    public String toStore() {
        String div = " | ";
        return "E" + div + (isDone ? "1" : "0") + div + description + div + at;
    }

    /**
     * Return the String represents the task.
     * @return the String represents the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (atDate == null ? at : atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }
}
