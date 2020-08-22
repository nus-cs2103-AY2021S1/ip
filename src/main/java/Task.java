/**
 * Represents a general task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon to indicate the status of the task.
     * @return a status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Sets the status of the task to Done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}