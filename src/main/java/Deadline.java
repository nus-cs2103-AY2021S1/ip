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
    protected LocalDate by;
    protected String time;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.time = null;
    }

    public Deadline(String description, LocalDate by, String time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
        this.time = null;
    }

    public Deadline(String description, boolean isDone, LocalDate by, String time) {
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
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + time + ")";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by + (time == null || time.isEmpty() ? "" : time) + ")";
    }
}