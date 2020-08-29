
/**
 * The {@code Task} class represents a task that
 * contains a description, isDone state and type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Initialises a {@code Task} object with its description and type.
     *
     * @param description Description of the task.
     * @param type        Type of task i.e. {@code Event}, {@code Deadline}, {@code Todo}
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the status of the task.
     *
     * @return Status of the task.
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the task description.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return ("[" + this.type + "][" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Returns task type.
     *
     * @return Type of task.
     */
    public String getType() {
        return this.type;
    }

    public String getTiming() {
        return null;
    }
}
