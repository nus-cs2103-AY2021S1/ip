package botbot.tasks;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    protected final char type;
    protected final String description;
    private boolean isDone;

    /**
     * Creates a task.
     * 
     * @param type Type of task.
     * @param description Description of task.
     */
    public Task(char type, String description) {
        this.type = type;
        this.description = description;
        isDone = false;
    }

    /**
     * Creates a task.
     *
     * @param type Type of task.
     * @param description Description of task.
     * @param isDone Completion status of task.
     */
    public Task(char type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the type of the task.
     * 
     * @return Type of task.
     */
    public char getType() {
        return type;
    }

    /**
     * Returns the description of the task.
     * 
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of the task.
     * 
     * @return 1 if task is done, 0 otherwise.
     */
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Returns the icon representing the completion status of the task.
     *
     * @return Tick if task is done, cross otherwise.
     */
    String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the time of the task.
     * 
     * @return Time of task.
     */
    public abstract String getAt();

    /**
     * Returns the deadline of the task.
     * 
     * @return Deadline of task.
     */
    public abstract String getBy();
}