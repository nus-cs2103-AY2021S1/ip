package duke.task;

/**
 * Represents a task with a description and deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new task with the specified description and deadline.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of a deadline task.
     *
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
