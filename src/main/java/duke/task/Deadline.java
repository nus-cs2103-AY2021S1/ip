package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a set deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Class constructor.
     *
     * @param description A string representing the task description.
     * @param by          A string representing the task deadline date/time.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Class constructor specifying whether or not the task has been completed.
     *
     * @param description A string representing the task description.
     * @param isDone      <code>true</code> if the task has been completed;
     *                    <code>false</code> otherwise.
     * @param by          A string representing the task deadline date/time.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String getTime() {
        return by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String printTime() {
        return by.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), printTime());
    }
}
