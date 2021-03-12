/**
 * Represents a Task object which is a parent class of:
 * Deadline, Event and Todo.
 */
public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;
    protected static final String DEADLINE_TASK = "D";
    protected static final String EVENT_TASK = "E";
    protected static final String TODO_TASK = "T";

    private final String TICK_ICON = "\u2713";
    private final String CROSS_ICON = "\u2718";

    /**
     * Creates a Task object.
     * It is mainly for file writing.
     *
     * @param description is the description of the task.
     * @param type is the type of subclass task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     * It is mainly for file reading.
     *
     * @param description is the description of the task.
     * @param type is the type of subclass task.
     * @param isDone states if the Task object is completed or not.
     */
    public Task(String description, String type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    /**
     * Returns a String representation of a tick or a cross.
     * Based on the Task's isDone status.
     *
     * @return a tick or a cross.
     */
    public String getStatusIcon() {
        return (isDone ? TICK_ICON : CROSS_ICON);
    }

    /**
     * Returns a boolean value that describes the completion of the Task object.
     * @return a boolean value.
     */
    public boolean getCompletionStatus() {
        return isDone;
    }

    /**
     * Returns the String description of the Task object.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the state of completion of a Task.
     * Not done to Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the String representation of Task type.
     *
     * @return Task type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the String representation of Task object.
     *
     * @return Task object overall description.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
