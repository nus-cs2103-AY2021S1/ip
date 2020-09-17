package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a TimedTask that needs to be done by a certain date and time. A
 * <code>Deadline</code> object is represented as a Task with a LocalDateTime.
 */
public class Deadline extends TimedTask {

    /**
     * Constructs a <code>Deadline</code> object with a description and a LocalDateTime.
     * This Deadline is marked as undone.
     *
     * @param description Describes what to do.
     * @param by String representation of date and time.
     */
    public Deadline(String description, String by) {
        super(description, by);
        this.taskType = TaskType.DEADLINE;
    }

    /**
     * Constructs a <code>Deadline</code> object with a description,
     * a boolean to indicate if the Deadline is done and a LocalDateTime.
     *
     * @param description Describes what to do.
     * @param isDone Indicates if the Deadline is done.
     * @param by String representation of date and time.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, by);
        this.taskType = TaskType.DEADLINE;
    }

    /**
     * Returns a description of the Deadline to be stored in a file.
     *
     * @return A description of the Deadline.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns a description of the Deadline.
     *
     * @return A description of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a")) + ")";
    }
}
