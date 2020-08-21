package duke.task;

/**
 * Represents a task that is to be completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Class constructor.
     *
     * @param description A string representing the task description.
     * @param type        The type of this task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Class constructor specifying whether or not the task has been completed.
     *
     * @param description A string representing the task description.
     * @param isDone      <code>true</code> if the task has been completed;
     *                    <code>false</code> otherwise.
     * @param type        The type of this task.
     */
    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Getter method.
     *
     * @return A string representing this task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method.
     *
     * @return <code>true</code> if the task has been completed;
     *         <code>false</code> otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gives a tick/cross symbol depending on whether or not this task has been completed.
     *
     * @return A tick if the task has been completed; a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); // Return tick or X symbol
    }

    /**
     * Changes this task's <code>isDone</code> to <code>true</code>.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Getter method.
     *
     * @return This task's type.
     */
    public TaskType getTaskType() {
        return type;
    }

    /**
     * Formats the time in the following format: <code>dd-MM-yyyy HH:mm</code>.
     *
     * @return A string representing the time in the specified format.
     */
    public abstract String getTime();

    /**
     * Formats the time in the following format: <code>d MMM yyyy, hh:mm a</code>.
     *
     * @return A string representing the time in the specified format.
     */
    public abstract String printTime();

    /**
     * Standard <code>toString</code>.
     *
     * @return A string representing this task object.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, getStatusIcon(), description);
    }
}
