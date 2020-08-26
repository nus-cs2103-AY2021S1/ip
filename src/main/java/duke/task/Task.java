package duke.task;

/**
 * This class represents a Task, storing its description and whether it has been done.
 */
public class Task {
    protected String description;
    protected boolean done;

    /**
     * Creates a Task which has not been completed.
     *
     * @param description description of the Task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates a Task.
     *
     * @param description description of the Task
     * @param isDone whether this Task has been done
     */
    public Task(String description, boolean isDone) {
        this.description = sanitizeString(description);
        done = isDone;
    }

    /**
     * Returns whether this Task has been done.
     *
     * @return true if the task has been done, false otherwise
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks this Task as done.
     */
    protected void markAsDone() {
        done = true;
    }

    /**
     * Returns a String describing this Task for Duke to display to the user.
     *
     * @return a String describing this Task
     */
    public String displayString() {
        String doneString = done ? "✓" : "✗";
        return String.format("[%s][%s] %s", taskTypeString(), doneString, description);
    }

    /**
     * Returns the abbreviated type of this task used in the displayString() method.
     *
     * @return a String describing the type of this Task
     */
    protected String taskTypeString() {
        return "T";
    }

    /**
     * Returns the description of this Task.
     *
     * @return the description of this Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sanitizes the given String by replacing tabs with 4 spaces so that the Task can be safely serialized
     * as a tab delimited file.
     *
     * @param s the string to sanitize
     * @return the sanitized string
     */
    protected static String sanitizeString(String s) {
        return s.replaceAll("\t", "    ");
    }
}
