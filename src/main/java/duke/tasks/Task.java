package duke.tasks;

/**
 * Represents a task with description and a boolean to signify if it is done.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with description that is not done.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task with description, specifying if it is done.
     * @param description Description of the task.
     * @param isDone True if task is done, False if task is not done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Retrieves information on whether task is done and represents with a symbol.
     *
     * @return A String representing a tick (if task is done) or cross (if task is not done) symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark task as done.
     *
     * @return A task that is marked as done.
     */
    public Task markAsDone() {
        return new Task(this.description, true);
    }

    /**
     * Returns the representation of the task in a text file.
     *
     * @return String representation of task in a text file.
     */
    public String toTxtFileFormat() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
