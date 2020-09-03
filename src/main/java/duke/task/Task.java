package duke.task;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    /** Description of the task. */
    protected String description;
    /** Marks if the task is done or not. */
    protected boolean isDone;
    /** Represents the type of the task. */
    protected TaskType taskType;

    /**
     * Creates a new task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the status icon.
     * "✓" indicates this task is completed and "✘" indicates this task is not completed.
     *
     * @return String of the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * If this task is done, returns true, otherwise false.
     *
     * @return True, if this task is done, otherwise false.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the description of this task.
     *
     * @return The description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the short form string representation of this task.
     *
     * @return String representation of the task type in short form.
     */
    public String getShortForm() {
        return taskType.getShortForm();
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.taskType.getShortForm(), this.getStatusIcon(), this.description);
    }
}
