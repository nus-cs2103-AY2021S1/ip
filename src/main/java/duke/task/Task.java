package duke.task;

/**
 * Task class that represents a task.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task using the given description.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbol indicating whether the task has been completed.
     * @return check mark if the task has been completed, or a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }
    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        isDone = false;
    }
    /**
     * Returns the String representation of the task.
     * @return the String representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the String representation of the task when it is stored in a data file.
     * @return the String representation of the task when it is stored in a data file.
     */
    public String toStorageString() {
        if (isDone) {
            return "? | 1 | " + description;
        } else {
            return "? | 0 | " + description;
        }
    }

    @Override
    public int compareTo(Task t) {
        String givenDescription = t.description;
        return description.compareTo(givenDescription);
    }
}
