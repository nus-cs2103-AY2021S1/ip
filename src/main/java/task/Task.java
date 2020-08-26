package task;

/**
 * Encapsulates the details of a task.
 *
 * <p>The 'Task' supports operators, supported include: </p>
 *
 * <p> (i) Getters to details </p>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create Task.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to create Task.
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets statusIcon of this object.
     * @return statusIcon of this object.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return \u2713 or \u2718 symbols
    }

    /**
     * Marks this object as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Check if this object is done.
     * @return status of this object, true if completed, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the type of Tasks.
     * @return type of Task.
     */
    public String getType() {
        return "1"; // dummy type;
    }

    /**
     * Gets description of this object.
     * @return description of this object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the date inputted by user in string.
     * @return date inputted by user.
     */
    public String getDateInput() {
        return "No date available";
    }

    /**
     * Gets the date inputted by user in string.
     * @return time inputted by user.
     */
    public String getTimeInput() {
        return "No time available";
    }

    /**
     * String representation of this object.
     * @return string representation of this object ([statusIcon] description).
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
