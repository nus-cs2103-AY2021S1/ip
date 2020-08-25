package task.tasks;

/**
 * Stores text entered by user as a task.
 */
public class Task {
    /**
     * Contents of task.
     */
    protected String description;
    /**
     * True if a task is completed. False if a task is not completed.
     */
    protected boolean isDone;

    /**
     * Creates a task.
     *
     * @param description Contents of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if a task is completed.
     *
     * @return Y if a task is completed. N if a task is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); // Return Y or N
    }

    /**
     * Marks a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of a task description and its completion status.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}