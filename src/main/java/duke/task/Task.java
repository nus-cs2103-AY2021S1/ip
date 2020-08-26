package duke.task;

/**
 * Represents a task.
 */
public class Task {

    public enum TaskType {
        TODOS,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;
    public static final String tick = "\u2713";
    public static final String cross = "\u2718";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return (isDone ? tick : cross); //return tick or X symbols
    }

    /**
     * Marks the task as done, and returns the String representation of the task.
     *
     * @return a String representation of the task.
     */
    public String markAsDone() {
        isDone = true;
        return toString();
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
