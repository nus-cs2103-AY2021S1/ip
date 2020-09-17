package duke.tasks;

/**
 * Task object with description.
 */

public class Task {
    protected boolean isCompleted;
    protected String description;
    protected String priorityLevel;

    /**
     * Constructor.
     * @param description description string of Task.
     */
    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
        this.priorityLevel = "nil";
    }

    /**
     * Set the task as completed.
     */
    public void markDone() {
        this.isCompleted = true;
    }

    /**
     * Sets the priority level of the task.
     * @param priorityLevel of the task.
     */
    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * Gets the task description.
     * @return String description of Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the status icon of the Task.
     * status icon either displays "✓" as completed or "✗" as incomplete.
     * @return the status icon of the Task.
     */
    public String getStatusIcon() {
        return (isCompleted) ? "\u2713" : "\u2718";
    }

    /**
     * Get the file representation of status icon of the Task.
     * status icon either displays "1" as completed or "0" as incomplete.
     * @return the file representation of the status icon of the task.
     */
    public String getFileStatusIcon() { return (isCompleted) ? "1" : "0"; }

    public String fileString() {
        return getFileStatusIcon() + " | " + description;
    }

    /**
     * String representation of the Task.
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " +
                "(P: " + priorityLevel + ") " + description;
    }
}
