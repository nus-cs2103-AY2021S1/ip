package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task that needs to be done before a specific time.
 */
public class Deadline extends Task {
    private final Date doBy;

    /**
     * Constructor
     *
     * @param description Description of the deadline.
     * @param isDone Status of the deadline - done or not done.
     * @param doBy Due date of the deadline.
     */
    public Deadline(String description, boolean isDone, Date doBy) {
        super(description, isDone);
        this.doBy = doBy;
    }

    /**
     * Returns the due date of the deadline, in String.
     * @return Due date of the deadline, in String
     */
    public String getDoByStr() {
        return new SimpleDateFormat("y-M-d").format(this.doBy);
    }

    /**
     * Returns the due date of the deadline.
     * @return Due date of the deadline.
     */
    public Date getDoBy() {
        return doBy;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[D][%s] %s (by: %s)", box, this.description,
                new SimpleDateFormat("MMM d yyyy").format(this.doBy));
    }
}
