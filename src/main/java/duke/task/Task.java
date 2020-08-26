package duke.task;

import java.time.LocalDate;

/**
 * Represents a task, which can be a todo, deadline or event.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks if the task is due on the given date.
     *
     * @param date A date
     * @return true if the task is due on the given date.
     */
    public abstract boolean isDue(LocalDate date);

    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }
    
    /**
     * Returns a status icon corresponding to whether the task is done.
     *
     * @return a tick if the task is done, and a cross otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task for saving into a file.
     *
     * @return a string representation of the task.
     */
    public String toSaveData() {
        return (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Returns a string representation of the task for displaying.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
