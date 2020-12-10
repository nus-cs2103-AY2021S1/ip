package ultron.tasks;

public abstract class Task {

    /**
     * Store the messages.
     */
    private final String message;

    /**
     * Boolean to check if task is completed.
     */
    private boolean isCompleted;

    /**
     * Abstract task class for all Tasks.
     *
     * @param description Description of the Task.
     */
    public Task(final String description) {
        this.message = description;
        this.markUnDone();
    }

    /**
     * Get the description of the task.
     *
     * @return Message for the task.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Check is the task is done.
     *
     * @return boolean denoting if the task is completed.
     */
    public boolean isDone() {
        return this.isCompleted;
    }

    /**
     * Get the type of task.
     *
     * @return String for type of task.
     */
    public abstract String getType();

    /**
     * Get the command for the class as a string.
     *
     * @return String for command.
     */
    public abstract String getCommand();

    /**
     * Mark the task as completed.
     */
    public void markDone() {
        this.isCompleted = true;
    }

    /**
     * Mark the task as not completed.
     */
    public void markUnDone() {
        this.isCompleted = false;
    }

    /**
     * Get the status icon denoting if the task is completed.
     *
     * @return Status icon as a String.
     */
    public String getStatusIcon() {
        return (this.isDone() ? "\u2713" : "\u2718"); //returns tick or X symbols
    }

    /**
     * Convert the task to string format.
     *
     * @return String format of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
            this.getStatusIcon(),
            this.getMessage());
    }
}
