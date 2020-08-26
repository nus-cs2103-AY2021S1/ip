import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Event extends Task {
    protected LocalDate at;
    protected String time;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate at, String time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
        this.time = null;
    }

    public Event(String description, boolean isDone, LocalDate at, String time) {
        super(description, isDone);
        this.at = at;
        this.time = time;
    }

    /**
     * This method formats the task for display to the user.
     * @return This returns a string containing the task details.
     */
    public String display() {
        if (time == null || time.isEmpty()) {
            return "[E]" + super.toString() + " (at: "
                    + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + time + ")";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at
                + (time == null || time.isEmpty() ? "" : " " + time) + ")";
    }
}