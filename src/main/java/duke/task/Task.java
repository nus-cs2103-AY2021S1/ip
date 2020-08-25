package duke.task;

/**
 * Represents a Task
 */
public class Task {

    /** Description of task. */
    public String description;
    /** Status of task. */
    public boolean isDone;

    /**
     * Constructs a new instance of a Task with attributes defined in parameters.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon of task.
     * @return A string value of status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
