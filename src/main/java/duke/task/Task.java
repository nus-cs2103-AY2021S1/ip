package duke.task;

/**
 * A Task. Contains a description, and a boolean to
 * indicate if it is done.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "✓" : "X") + "] " + description;
    }

    /**
     * Getter method for this Task's description.
     * @return Description of this Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for this Task's isDone status.
     * @return Boolean indicating if this Task is completed.
     */
    public boolean isTaskDone() {
        return isDone;
    }

    public abstract String getDescriptionForDatabase();
}
