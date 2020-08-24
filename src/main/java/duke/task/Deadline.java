package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done by a certain date and time. A
 * <code>Deadline</code> object is represented as a Task with a LocalDateTime value.
 */
public class Deadline extends Task{

    /** Indicates when the Deadline needs to be completed by */
    protected LocalDateTime by;

    /**
     * Constructs a <code>Deadline</code> object with a description and a LocalDateTime value.
     * This Deadline is marked as undone.
     *
     * @param description Describes what to do.
     * @param by String representation of date and time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Constructs a <code>Deadline</code> object with a description,
     * a boolean to indicate if the Deadline is done and a LocalDateTime value.
     *
     * @param description Describes what to do.
     * @param isDone Indicates if the Deadline is done.
     * @param by String representation of date and time.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns the LocalDateTime in which the Deadline has to completed by.
     *
     * @return A LocalDateTime object.
     */
    public LocalDateTime getDateTime() {
        return this.by;
    }

    /**
     * Returns a description of the Deadline to be stored in a file.
     *
     * @return A description of the Deadline.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns a description of the Deadline.
     *
     * @return A description of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a")) + ")";
    }
}
