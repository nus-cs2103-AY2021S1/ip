/**
 * Represents a Task object that contains the base behaviour of a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Initialises a new Task with the given description.
     * @param description Description of the task that needs to be performed
     */
    public Task(String description, Priority priority) {
        assert description != null;

        this.description = description;
        this.priority = priority;
        this.isDone = false;
    }

    /**
     * Initialises a new Task with the given description and whether it is completed.
     * @param description Description of the task that needs to be performed
     * @param isDone Completion status of the task
     */
    public Task(String description, Priority priority, boolean isDone) {
        assert description != null;

        this.description = description;
        this.priority = priority;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + " {" + priority.name() + "} " + description;
    }

    /**
     * Retrieves a string representation of the task to be used for saving to the .txt file.
     *
     * @return Task in its save format
     */
    public String getSaveFormat() {
        return (isDone ? "1" : "0") + " | " + priority.name() + " | " + description;
    }
}
