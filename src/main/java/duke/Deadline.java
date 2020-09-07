package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    protected String by;
    /** Deadline of the task as java.time.LocalDate. */
    protected LocalDate date;

    /**
     * Constructor for Deadline.
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            this.date = null;
        }
    }

    /**
     * Constructor for Deadline.
     * @param description the description of the task.
     * @param isDone boolean to indicates whether the task has been done or not.
     * @param by the deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            this.date = null;
        }
    }

    /**
     * Gets the format of the Deadline to be saved in hard disk.
     * @return Deadline object in specified format.
     */
    @Override
    public String getData() {
        return "DEADLINE#" + description + "#" + String.valueOf(isDone) + "#" + by + "#" + tag;
    }

    /**
     * Gets the string representation of the Deadline object.
     * @return the string representation of Deadline.
     */
    @Override
    public String toString() {
        String parsedDate = date != null
            ? date.getDayOfWeek() + ", " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            : by;
        return "[D]" + super.toString() + " (by: " + parsedDate + ")";
    }
}
