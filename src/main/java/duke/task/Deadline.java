package duke.task;

import duke.DukeInputException;
import duke.Priority;
import duke.util.DateTimeHandler;

/**
 * Represents a deadline as a task with a date and time.
 */
public class Deadline extends Task {

    /** Date and time of the deadline. */
    private final String dateTime;

    /**
     * Creates a new deadline from a description and a date and time String.
     *
     * @param priority Priority of the deadline.
     * @param description Description of the deadline.
     * @param dateTime Due date and time of the deadline.
     * @throws DukeInputException If the date and time input is invalid.
     */
    public Deadline(Priority priority, String description, String dateTime) throws DukeInputException {
        super(priority, description);
        this.dateTime = DateTimeHandler.parseDateTime(dateTime);
    }

    /**
     * Creates a new deadline from a description, completion status and a date and time String.
     *
     * @param priority Priority of the deadline.
     * @param description Description of the deadline.
     * @param isDone Completion status of the deadline.
     * @param dateTime Due date and time of the deadline properly formatted.
     */
    public Deadline(Priority priority, String description, boolean isDone, String dateTime) {
        super(priority, description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Gets the date and time of the deadline.
     *
     * @return Date and time of the deadline.
     */
    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String getData() {
        return "D / " + super.getData() + " / " + this.getDateTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateTime() + ")";
    }
}
