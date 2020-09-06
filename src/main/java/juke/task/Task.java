package juke.task;

/**
 * A juke.task.Task class is a general class that represents a juke.task.Task.
 * It has a Description, and boolean whether it is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the appropriate icon to represent current Done status of juke.task.
     * @return Icon String.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks juke.task.Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the representative text of the juke.task.Task.
     * @return Representative text.
     */
    public String taskSaver() {
        String doneStatus = this.isDone ? "1" : "0";
        return doneStatus + "/" + description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Outputs the juke.task.Task as a String.
     * @return String representation of juke.task.Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
