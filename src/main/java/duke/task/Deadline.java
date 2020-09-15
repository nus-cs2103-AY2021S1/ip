package duke.task;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * Constructs a Deadline object.
     *
     * @param description A string describing
     *                    the task.
     * @param by A string recording the time
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
