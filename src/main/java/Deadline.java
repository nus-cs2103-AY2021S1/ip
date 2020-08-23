/**
 * Deadline is a subtype of Task which has a stipulated deadline
 * for the task to be completed by.
 */

public class Deadline extends Task {

    /** The deadline of the task. */
    protected String by;

    /**
     * Constructor for Deadline.
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super("D", description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super("D", description, isDone);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return description + " / " + by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
