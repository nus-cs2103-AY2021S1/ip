package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs task.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs task.
     * @param description Task description.
     * @param isDone Done indicator.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status of the task.
     * @return A tick sign if the task is done and a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]");
    }

    /**
     * Returns the type of the task.
     * @return E for event, T for todo, D for deadline.
     */
    public abstract String getType();

    /**
     * Returns the task decription.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get status of the task.
     * @return True if the task is done and false otherwise.
     */

    public Boolean getStatus() {
        return isDone;
    }

    /**
     * Mark this task as done.
     * @return Done version of the old task.
     */
    public abstract Task markAsDone();

    /**
     * Returns string representation of this task.
     * @return String object of this task.
     */
    @Override
    public abstract String toString();
}
