package duke.task;

/**
 * Task class that abstracts a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a new Task with a given description.
     * This method is intended to serve as a dummy task generator for placeholder purposes.
     * Please do NOT construct tasks using this method. Instead, use the constructors of its
     * subclasses, i.e. <code>Deadline</code>, <code>Event</code>, and <code>Todo</code>.
     * @param description the name of the task to be created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon (tick or cross) of the current task.
     * @return an icon (tick or cross) to indicate whether the current task has been done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the current task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Return the string that is intended to be stored in the local database.
     * @return the string to be stored in the local database, the format is understandable
     *         for <code>Storage</code>
     */
    public String toDataString() {
        if (isDone) {
            return "? | 1 | " + description;
        } else {
            return "? | 0 | " + description;
        }
    }

    /**
     * Return the string representation of the task.
     * @return the string representation of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}