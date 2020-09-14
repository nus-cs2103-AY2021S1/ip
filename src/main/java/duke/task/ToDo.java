package duke.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Initializes a ToDo task.
     *
     * @param description Description of the task.
     * @param isDone      Whether the task is finished or not.
     * @param priority    Priority level of the task.
     */
    public ToDo(String description, boolean isDone, String priority) {
        super(description, isDone, priority);
    }

    /**
     * Gets the string representation of the task to be stored in the data file.
     *
     * @return The string representation of the task to be stored in the data file.
     */
    @Override
    public String getStorageString() {
        return "T | " + this.getStatusIcon() + " | " + this.description
                + " | " + this.priority.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description
                + " (Priority: " + this.priority.toString() + ")";
    }
}
