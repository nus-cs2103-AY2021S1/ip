package duke.task;

import java.time.LocalDate;

/**
 * Represents a task.
 *
 * <p>There are 3 types of tasks, namely Todo, Deadline and Event tasks.
 */
public class Task {

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
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the date of the task if the task has a date.
     * Otherwise, the current date is being returned.
     *
     * @return The date of the task.
     */
    public LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * Returns the status icon of the task i.e. a tick or a cross.
     *
     * @return A tick icon if the task is done, and a cross otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; //return tick or X symbols
    }

    /**
     * Determines whether the task is done.
     *
     * @return True if the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String symbol = this.getStatusIcon();
        return "[" + symbol + "] " + this.description;
    }
}