package duke;

/**
 * Represents a task.
 */
public class Task {

    /** Description of task */
    protected String description;

    /** Completion status */
    protected boolean isDone;

    /** Type of task */
    protected Type type;

    /**
     * Constructs a new instance of a task with its description.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new instance of a task with its description and type.
     * @param description Description of task.
     * @param type Type of task.
     */
    public Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Constructs a new instance of a task with its description, type and completion status.
     * @param description Description of task.
     * @param type Type of task.
     * @param isDone Completion status.
     */
    public Task(String description, Type type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    /**
     * Returns description of task.
     * @return Description of task.
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Returns date and time in subclasses, when valid.
     * @return Date and time as a string.
     */
    public String getTime() {
        return "";
    }

    /**
     * Returns date of task.
     * @return Date of task.
     */
    public String getDate() {
        return "";
    }

    /**
     * Returns status icon of task.
     * @return Status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns type of task.
     * @return Type of task.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Returns string representation of task.
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getTask();
    }
}
