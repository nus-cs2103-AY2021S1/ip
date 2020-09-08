package duke.task;

/**
 * A type of Task that has description and a "by" date
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for creating Deadline object
     *
     * @param description name of task
     * @param by deadline date and time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Alternative constructor that accounts for progress of task
     *
     * @param description name of task
     * @param by deadline date and time
     * @param isDone whether task is completed
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * toString method for Deadline
     *
     * @return task in string form
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
