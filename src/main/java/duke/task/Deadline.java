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
     * Returns true since deadlines have dates associated to them.
     *
     * @return True, since deadlines have dates associated to them.
     */
    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * Gets the date by which this task must be done.
     *
     * @return The date by which this task must be done.
     */
    @Override
    public LocalDate getDate() {
        return this.by;
    }

    /**
     * Returns a string representation of this deadline.
     *
     * @return String representation of this deadline.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
