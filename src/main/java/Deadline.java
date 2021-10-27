import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task with a deadline
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor
     *
     * @param description is the description of the task
     * @param by is the due-date of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor
     *
     * @param description is the description of the task
     * @param isDone is whether or not the task is done
     * @param by is the due-date of the task
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gives the String representation of the Task
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Checks if two Deadlines are equal
     *
     * @return a boolean
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Deadline) {
            Deadline otherDeadline = (Deadline) object;
            return this.description.equals(otherDeadline.description)
                    && this.isDone == otherDeadline.isDone
                    && this.by.equals(otherDeadline.by);
        } else {
            return false;
        }
    }
}
