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
     *
     * @param description The description of the deadline.
     * @param by The date by which the task must be done.
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Gets the date by which this task must be done.
     *
     * @return The date by which this task must be done.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of this deadline.
     *
     * @return String representation of this deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
