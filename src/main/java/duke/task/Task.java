package duke.task;

/**
 * Represents a task that needs to be done. A
 * <code>Task</code> object is represented by a String description
 * and a boolean indicating if the Task is done.
 */
public class Task {

    /** String to describe the task */
    protected final String description;

    /** Boolean to indicate if the Task is done */
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> object with a description.
     * This Task is marked as undone.
     *
     * @param description Describes what to do.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a <code>Task</code> object with a description and
     * a boolean to indicate if the Task is done.
     *
     * @param description Describes what to do.
     * @param isDone Indicates if the Task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the Task as done by changing the value of isDOne to true.
     *
     * @return Nothing.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Returns the status icon of the Task. A tick represents that
     * the Task is done while a cross represents the Task is undone.
     *
     * @return An icon representing the completion status of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a description of the Task to be stored in a file.
     *
     * @return A description of the Task.
     */
    public String toFileString() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns a description of the Task.
     *
     * @return A description of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
