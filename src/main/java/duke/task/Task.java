package duke.task;

/**
 * This class represents a Task, storing its description and whether it has been done.
 */
public class Task {
    protected String description;
    protected boolean done;
    private Runnable onChangeFunction;

    /**
     * Creates a Task which has not been completed.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates a Task.
     *
     * @param description Description of the Task.
     * @param isDone Whether this Task has been done.
     */
    public Task(String description, boolean isDone) {
        this.description = sanitizeString(description);
        done = isDone;
        onChangeFunction = () -> {};
    }

    /**
     * Returns whether this Task has been done.
     *
     * @return True if the task has been done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks this Task as done.
     */
    public void markAsDone() {
        done = true;
        onChange();
    }

    /**
     * Returns a String describing this Task for Duke to display to the user.
     *
     * @return String describing this Task.
     */
    public String displayString() {
        String doneString = done ? "✓" : "✗";
        return String.format("[%s][%s] %s", taskTypeString(), doneString, description);
    }

    /**
     * Returns the abbreviated type of this task used in the displayString() method.
     *
     * @return String describing the type of this Task.
     */
    protected String taskTypeString() {
        return "T";
    }

    /**
     * Returns the description of this Task.
     *
     * @return Description of this Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description of this Task.
     *
     * @param description New description for this Task.
     */
    public void setDescription(String description) {
        this.description = sanitizeString(description);
        onChange();
    }

    void setOnChangeFunction(Runnable function) {
        onChangeFunction = function;
    }

    void onChange() {
        onChangeFunction.run();
    }

    /**
     * Sanitizes the given String by replacing tabs with 4 spaces so that the Task can be safely serialized
     * as a tab delimited file.
     *
     * @param s String to sanitize.
     * @return Sanitized string.
     */
    protected static String sanitizeString(String s) {
        return s.replaceAll("\t", "    ");
    }
}
