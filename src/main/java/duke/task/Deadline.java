package duke.task;

import duke.format.DateFormat;
import java.util.Date;


/**
 * Represents a deadline type of task that contains an additional due date.
 */
public class Deadline extends Task {
    protected Date by;

    /**
     * Creates an instance of deadline
     * @param name the name of deadline
     * @param isComplete the completion status of deadline
     * @param by the due date of deadline
     */
    public Deadline(String name, boolean isComplete, Date by) {
        super(name, isComplete, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Gets the due date of deadline
     * @return the due date
     */
    @Override
    public Date getDate() {
        return this.by;
    }

    /**
     * Represents the deadline in string.
     * @return the string representation of deadline
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), DateFormat.formatDate(this.by));
    }
}
