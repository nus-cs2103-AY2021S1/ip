package duke;

/**
 * Handles maintenance and display of duke.Task objects.
 */

public class Task {
    /** Separator for task details */
    public static String SAVE_DELIMITER = "|";
    /** Escaped version of separator */
    public static String ESCAPED_SAVE_DELIMITER = "\\|";

    /** Name of task */
    protected String name;

    /** Completion state of task */
    protected boolean isCompleted;

    /**
     * Constructor for Tasks.
     * @param name Description of duke.Task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Constructor for Tasks.
     * @param name Description of duke.Task.
     * @param completed Completion state of duke.Task.
     */
    public Task(String name, boolean completed) {
        this.name = name;
        this.isCompleted = completed;
    }

    /**
     * Marks duke.Task as complete.
     */
    public void complete() {
        this.isCompleted = true;
    }

    /**
     * Gets appropriate status icon based on completion state of the duke.Task.
     * @return Status icon.
     */
    public String getStatusIcon() {
        return this.isCompleted ? "\u2713" : "\u2718";
    }

    /**
     * Represents duke.Task in format to be saved.
     * @return Saved representation of duke.Task object.
     */
    public String format() {
        return this.name + SAVE_DELIMITER + this.isCompleted;
    }

    /**
     * Represents duke.Task in String form.
     * @return String representation of duke.Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}
