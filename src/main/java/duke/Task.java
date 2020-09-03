package duke;

/**
 * Represents a task with a description and if task is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task.
     *
     * @param description Description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes a task containing the task description and if the task is done.
     * This is an overloaded constructor to allow for tasks in the hard drive to be loaded when Duke first runs.
     *
     * @param description Description of the task.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves completion status of task.
     *
     * @return Status of task.
     */
    public String getStatus() {
        return (isDone ? "done" : "not done");
    }

    /**
     * Gets the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks task as done.
     */
    protected void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if task is done.
     *
     * @return If Task is Done.
     */
    protected boolean isDone() {
        return isDone;
    }

    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
