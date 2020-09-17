package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with a description and marked as not done by default.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task with a description and done status.
     * @param description Description of task.
     * @param isDone Done status of task.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected boolean getStatus() {
        return isDone;
    }

    protected String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    protected void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    protected void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
