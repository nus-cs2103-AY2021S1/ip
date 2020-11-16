package task.tasks;

import datetimeconverter.DateTimeConverter;

/**
 * Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    /**
     * Due date of deadline.
     */
    protected String by;

    /**
     * Creates a deadline.
     *
     * @param description Contents of deadline.
     * @param by          Due date of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of a deadline description and its completion status.
     *
     * @return String representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeConverter.formatDateTime(by) + ")";
    }
}
