package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that need to be done before a specific date.
 */
public class Deadline extends Task {
    /** The date by which this task must be done. */
    protected LocalDate by;

    /**
     * Creates a new Deadline with the specified description and specified deadline date.
     * @param description The description of the deadline.
     * @param by The date by which the task must be done.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the date by which this task must be done.
     * @return The date by which this task must be done.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Gets the short form of this deadline.
     * @return The short form of this deadline.
     */
    @Override
    public String getShortForm() {
        return "D";
    }

    /**
     * Returns a string representation of this deadline.
     * @return A string representation of this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
