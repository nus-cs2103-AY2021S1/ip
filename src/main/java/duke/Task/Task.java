package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task object.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected TaskPriority taskPriority;

    /**
     * Constructs a <code>Task</code> Object to represent a task.
     *
     * @param description The description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskPriority = TaskPriority.LOW; // sets the task to lowest priority by default
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "✓" : "✘");
    }

    public int getStatusCode() {
        return this.isDone ? 1 : 0;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of a task as done.
     *
     * @throws DukeException If a task is already marked as done before.
     */
    public void markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("this task is already completed!");
        }
        this.isDone = true;
    }

    /**
     * Sets the priority value of a task.
     *
     * @param value The priority value of a task.
     * @throws DukeException If an invalid priority value is passed in.
     */
    public void setPriority(int value) throws DukeException {
        if (value == 1) {
            taskPriority = TaskPriority.URGENT;
        } else if (value == 2) {
            taskPriority = TaskPriority.HIGH;
        } else if (value == 3) {
            taskPriority = TaskPriority.MEDIUM;
        } else if (value == 4) {
            taskPriority = TaskPriority.LOW;
        } else {
            throw new DukeException("Invalid priority value!");
        }
    }

    public int getPriorityValue() {
        return this.taskPriority.getPriorityValue();
    }

    /**
     * Converts a task into serialized form.
     *
     * @return The serialized form of a task.
     */
    public abstract String serialize();

    @Override
    public String toString() {
        return String.format("[%s][%d] %s", getStatusIcon(), getPriorityValue(), getDescription());
    }
}
