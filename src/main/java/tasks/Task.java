package main.java.tasks;

/**
 * Represents an abstract task, consisting of a description.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
        return  doneStatus + " | " + this.description;
    }
}
