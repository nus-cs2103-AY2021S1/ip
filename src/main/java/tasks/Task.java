package tasks;

import exceptions.DataException;

/**
 * An abstract task. All tasks have a description and completion status.
 * They start as incomplete, and can be marked as complete later.
 */
public abstract class Task {

    // task description
    private final String description;

    // indicates whether the task is done or not
    private boolean isDone;

    /**
     * Constructs a new Task.
     * @param description the task description
     * @throws DataException if the task description is blank
     */
    public Task(String description) throws DataException {
        if (description.isBlank()) {
            throw new DataException("Task Description", "Cannot be blank");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Retrieves a character representing the task type. This should be unique to each subclass.
     * @return a character representing the task type
     */
    protected abstract char getTaskType();

    public void markAsDone() {
        this.isDone = true;
    }

    private char getStatusIcon() {
        // returns tick or X symbols
        return isDone ? '\u2713' : '\u2718';
    }

    public abstract String getParentCommand();

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", getTaskType(), getStatusIcon(), getDescription());
    }

}
