import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task that comprises a deadline task description, date and whether it is done.
 */

public class Deadline extends Task {

    protected LocalDate time;

    /**
     * Create a deadline task.
     * @param description of task.
     * @param isDone done status of task.
     * @param time of task.
     */
    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns the deadline date.
     * @return deadline date.
     */
    public String getDate() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the String description for deadline.
     * @return String description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }
}
