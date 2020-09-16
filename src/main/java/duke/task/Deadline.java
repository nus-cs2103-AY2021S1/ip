package duke.task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * Constructs a deadline task.
     *
     * @param description a string describing
     *                    the task.
     * @param by a string recording the time
     *           of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.time = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
