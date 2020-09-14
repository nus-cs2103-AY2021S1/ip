package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;
    protected LocalDateTime byDateTime;

    /**
     * Initializes a deadline task containing the task description and deadline of the task.
     *
     * @param description Description of the deadline task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);

        this.byDateTime = DateTimeHandler.tryParseDateTime(by);
        if (byDateTime == null) {
            this.byDate = DateTimeHandler.tryParseDate(by);
        }
        this.by = by;
    }

    /**
     * Initializes a deadline task containing the task description, if the task is done and deadline of the task.
     * This is an overloaded constructor to allow for tasks in the hard drive to be loaded when Duke first runs.
     *
     * @param description Description of the deadline task.
     * @param isDone      If task is done.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.byDateTime = DateTimeHandler.tryParseDateTime(by);
        if (byDateTime == null) {
            this.byDate = DateTimeHandler.tryParseDate(by);
        }
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return String representing the deadline of the task.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getInitial() + "]" +
                super.toString() +
                " (by: " + DateTimeHandler.generateDateTimeFormat(by, byDate, byDateTime) + ")";
    }
}
