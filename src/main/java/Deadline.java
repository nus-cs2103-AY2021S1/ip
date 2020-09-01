import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Deadline extends Task {
    /**
     * Date in which the deadline task is due.
     */
    private LocalDate by;
    /**
     * Time in which the deadline task is due.
     */
    private String time;

    /**
     * Deadline constructor with date only.
     * @param description   The description of the deadline task.
     * @param by            The date that the deadline task is due.
     */
    public Deadline(final String description, final LocalDate by) {
        super(description);
        this.by = by;
        this.time = null;
    }

    /**
     * Deadline constructor with date and time.
     * @param description The description for the deadline task.
     * @param by    The date that the deadline task is due.
     * @param time  The time that the deadline task is due.
     */
    public Deadline(final String description, final LocalDate by,
                    final String time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    /**
     * Deadline constructor with date and marked as done.
     * @param description   The description of the deadline task.
     * @param isDone        The deadline task is marked as done.
     * @param by            The date that the deadline task is due.
     */
    public Deadline(final String description, final boolean isDone,
                    final LocalDate by) {
        super(description, isDone);
        this.by = by;
        this.time = null;
    }

    /**
     * Deadline constructor with date and time and marked as done.
     * @param description   The description of the deadline task.
     * @param isDone        The deadline task is marked as done.
     * @param by            The date that the deadline task is due.
     * @param time          The time that the deadline task is due.
     */
    public Deadline(final String description, final boolean isDone,
                    final LocalDate by, final String time) {
        super(description, isDone);
        this.by = by;
        this.time = time;
    }

    /**
     * This method formats the task for display to the user.
     * @return This returns a string containing the task details.
     */
    public String display() { //format time here
        if (time == null || time.isEmpty()) {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + time + ")";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by + (time == null || time.isEmpty() ? "" : time)
                + ")";
    }
}
