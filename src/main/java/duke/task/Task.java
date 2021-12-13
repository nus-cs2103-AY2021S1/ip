package duke.task;

/**
 * Represents a task with description and done status.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor
     *
     * @param description Description of the task.
     * @param isDone Status of the task - done or not done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the done status of the task.
     * @return Done status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[%s] %s", box, this.description);
    }
}



