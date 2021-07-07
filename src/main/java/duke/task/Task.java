package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object, default to status in not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task object with done/not done status.
     *
     * @param isDone Whether the task is done.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon of task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }


    /**
     * Marks a task as done.
     * @return The task marked as done.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Gets the task details in the format for saving to local storage.
     *
     * @return Task details in the correct format.
     */
    public abstract String getTaskDetailsForSave();

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }
}
