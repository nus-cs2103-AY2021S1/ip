package duke;

/**
 * Task is the object that stores the information of user input.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task object.
     * 
     * @param description Takes in the description of the task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbol to indicate if the task is done.
     * 
     * @return a checkmark for a task that is done and a cross for a task that is not.
     */
    public String getStatusIcon() {
        return isDone
                ? "\u2713"
                : "\u2718";
    }

    /**
     * Gets the description of the task object.
     * 
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the status of the task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task type of Task.
     *
     * @return the task type of Task.
     */
    public String getTaskType() {
        return "Task";
    }

    /**
     * Formats the string of a Task object.
     *
     * @return a formatted string for a Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Formats the string of a Task object to be stored into hard drive.
     *
     * @return a formatted string suitable for storage in hard drive for a Task object.
     */
    public String toStringInFile() {
        int taskDone = isDone ? 1 : 0;
        return " | " + taskDone + " | " + getDescription();
    }
}
