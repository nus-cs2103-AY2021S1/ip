package duke.task;

/**
 * Represents a Task that needs to be done. A
 * <code>Task</code> object is represented by a Task description
 * and a boolean indicating if the Task is done.
 */
public class Task {

    /** String to describe the Task */
    protected final String description;

    /** Boolean to indicate if the Task is done */
    protected boolean isDone;

    /** TaskType to represent the type of task */
    protected TaskType taskType;

    /**
     * Constructs a <code>Task</code> object with a description.
     * This Task is marked as undone.
     *
     * @param description Describes what needs to be done.
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
     * Marks the Task as done by changing the value of isDone to true.
     */
    public void completeTask() {
        isDone = true;
    }

    /**
     * Returns the status icon of the Task. A tick represents that
     * the Task is done while a cross represents the Task is undone.
     *
     * @return An icon representing the completion status of the Task.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description of the Task.
     */
    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
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
