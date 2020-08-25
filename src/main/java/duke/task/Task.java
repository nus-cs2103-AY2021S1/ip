package duke.task;

/**
 * Abstract Task class.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of the task.
     * @return an icon to indicate the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the the status of the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Abstract method for subclass to implement to return a String which to be store in files.
     * @return the String to be store in files.
     */
    public abstract String toStore();

    /**
     * Return the String represents the task.
     * @return the String represents the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
