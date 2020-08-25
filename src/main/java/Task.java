/**
 * Represents a task that the user has to do.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task that is by default not done yet, and with a description.
     * @param description The description for this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes a task with both the description, and whether it is done.
     * @param description The description for this task.
     * @param isDone Whether this task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done.
     * @return Whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a tick icon if this task is done, and a cross icon if this task is not yet done.
     * @return The Icon as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}