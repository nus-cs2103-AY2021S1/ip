package tasks;

/**
 * Represents an abstract task, consisting of a description.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;

    /**
     * Constructs a task given a description, setting default done status as false.
     * @param description provided for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task given a description and done status.
     * @param description provided for the task
     * @param isDone provided for the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Obtains the description of the task.
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon that represents the done status of a task.
     *
     * @return the status icon, tick meaning done
     * while cross meaning not done yet
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string that represents the task.
     *
     * @return the string consisting of the done status and description
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string that represents the task in a database.
     *
     * @return the string consisting of the done status and description
     */
    public String databaseString() {
        String doneStatus = this.isDone ? "true" : "false";
        return doneStatus + " | " + this.description;
    }
}
