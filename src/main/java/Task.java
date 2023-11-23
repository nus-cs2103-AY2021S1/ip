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
     * Returns true if task is completed.
     * @return True if task is completed.
     */
    public boolean isTaskCompleted() {
        return this.isCompleted;
    }

    /**
     * To indicate that the task is completed (with a tick symbol).
     */
    public void markTaskAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * To indicate that the task is uncompleted (with a cross symbol).
     */
    public void markTaskAsUncompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns the details of the task.
     * @return Task details.
     */
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
