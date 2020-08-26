package Duke.task;

/**
 * This is a subclass of Task.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * Initialize a Deadline object.
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
        return "[D]" + super.toString() +
            " (by: " + time + ")";
    }
}
