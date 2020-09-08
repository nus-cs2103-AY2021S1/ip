package juke.task;

/**
 * Represents a Task class with a given description
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with a given description.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the appropriate icon to represent current Done status of task.
     *
     * @return Icon String.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the representative text of the Task.
     *
     * @return Text that represents Task.
     */
    public String taskSaver() {
        String doneStatus = this.isDone ? "1" : "0";
        return doneStatus + "/" + this.description;
    }

    /**
     * Returns the description of Task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the Task Description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Outputs the Task as a String.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
