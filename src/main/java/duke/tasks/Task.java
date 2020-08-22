package duke.tasks;

/**
 * A class that represents a task with a description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the task class.
     *
     * @param description the string of description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the status icon.
     *
     * @return a string representation of the status icon, a tick if it is done, a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a string of the task description.
     *
     * @return a string description of the task.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns a boolean value of whether the task is done or not.
     *
     * @return true if task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the isDone to be true if task is done, false otherwise.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task, including status icon and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}