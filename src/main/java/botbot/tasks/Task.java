package botbot.tasks;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    protected final char type;
    protected final String description;
    private TaskStatus status;

    /**
     * Creates a task.
     *
     * @param type Type of task.
     * @param description Description of task.
     */
    public Task(char type, String description) {
        this.type = type;
        this.description = description;
        status = TaskStatus.NOT_DONE;
    }

    /**
     * Creates a task.
     *
     * @param type Type of task.
     * @param description Description of task.
     * @param status Completion status of task.
     */
    public Task(char type, String description, TaskStatus status) {
        this.type = type;
        this.description = description;
        this.status = status;
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of task.
     */
    public char getType() {
        assert type == Deadline.TYPE_CODE || type == Event.TYPE_CODE || type == Todo.TYPE_CODE
                : "Task type not D, E or T";
        return type;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        assert description != null : "Empty description";
        return description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return 1 if task is done, 0 otherwise.
     */
    public String getStatus() {
        return status.getStrValue();
    }

    /**
     * Returns the icon representing the completion status of the task.
     *
     * @return Tick if task is done, cross otherwise.
     */
    String getStatusIcon() {
        return status.getStatusIcon();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        status = TaskStatus.DONE;
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
