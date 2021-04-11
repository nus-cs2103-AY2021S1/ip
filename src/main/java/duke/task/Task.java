package duke.task;

/**
 * The Task class encapsulates tasks which are pieces of work to be done.
 */
public abstract class Task {

    /** The description of the task */
    protected String description;

    /** Status of whether a task has been completed */
    protected boolean isDone;

    /** The type of task */
    protected String type;

    /**
     * Constructs a task which defaults to be not completed.
     * @param type the type of task.
     * @param description the description of the task.
     */
    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the completion status.
     * @param type the type of task.
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     */
    public Task(String type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the type of the task as an identifier.
     * @return the type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the completion flag of the task and generates the
     * unicode string representation of the task status;
     * a tick for completed, a cross for not completed.
     * @return the unicode of the completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Checks if the task is completed based on the completion flag.
     * @return boolean value for completion status.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Marks a task as completed by setting completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the description of the task.
      * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Formats the string representation of the task and returns it.
     * @return the formatted string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, getStatusIcon(), description);
    }
}
