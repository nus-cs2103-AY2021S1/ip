/**
 * Represents a task to be completed.
 */
public class Task {
    protected String details;
    protected boolean isCompleted;

    /**
     * Constructor for the task.
     * @param details task details.
     */
    public Task(String details) {
        this.details = details;
    }

    /**
     * To indicate that the task is completed (with a tick symbol).
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getDetails() {
        return this.details;
    }

    /**
     * Returns the string representation of the task.
     * @return String representation
     */
    @Override
    public String toString() {
        return "[" + (isCompleted ? "✓" : "✗") + "] " + details;
    }
}
