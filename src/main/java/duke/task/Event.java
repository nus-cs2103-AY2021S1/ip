package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a set time-frame.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Class constructor.
     *
     * @param description A string representing the task description.
     * @param at          A string representing the task event date/time.
     */
    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Class constructor specifying whether or not the task has been completed.
     *
     * @param description A string representing the task description.
     * @param isDone      <code>true</code> if the task has been completed;
     *                    <code>false</code> otherwise.
     * @param at          A string representing the task event date/time.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone, TaskType.EVENT);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String getTime() {
        return at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String printTime() {
        return at.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), printTime());
    }
}
