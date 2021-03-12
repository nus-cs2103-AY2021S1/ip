package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 *
 * <p>There are 3 types of tasks, namely Todo, Deadline and Event tasks.
 */
public class Task {

    private static final String TICK_ICON = "\u2713";
    private static final String CROSS_ICON = "\u2718";

    /**
     * The description of the task.
     */
    private final String description;

    /**
     * A boolean to determine if the task is already done.
     */
    private boolean isDone;

    /**
     * Initializes a task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert !description.isEmpty() : "Task description should not be empty.";
        this.description = description;
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date of the task if the task has a date.
     * Otherwise, the current date is being returned.
     *
     * @return The date of the task.
     */
    public LocalDateTime getDate() {
        return LocalDateTime.now();
    }

    /**
     * Returns the status icon of the task i.e. a tick or a cross.
     *
     * @return A tick icon if the task is done, and a cross otherwise.
     */
    public String getStatusIcon() {
        return isDone ? TICK_ICON : CROSS_ICON;
    }

    /**
     * Determines whether the task is done.
     *
     * @return True if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String symbol = getStatusIcon();
        return "[" + symbol + "] " + description;
    }
}
