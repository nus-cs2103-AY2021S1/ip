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
    /**
     * The date of the event.
     */
    private LocalDate at;
    /**
     * The time of the event.
     */
    private String time;

    /**
     * Event constructor with date only.
     * @param description   The description of the event.
     * @param at            The date of the event.
     */
    public Event(final String description, final LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Event constructor with date and time.
     * @param description   The description of the event.
     * @param at            The date of the event.
     * @param time          The time of the event.
     */
    public Event(final String description, final LocalDate at,
                 final String time) {
        super(description);
        this.at = at;
        this.time = time;
    }

    /**
     * Event constructor with date and marked as done.
     * @param description   The description of the event.
     * @param isDone        The event is marked as done.
     * @param at            The date of the event.
     */
    public Event(final String description, final boolean isDone,
                 final LocalDate at) {
        super(description, isDone);
        this.at = at;
        this.time = null;
    }

    /**
     * Event constructor with date and time and marked as done.
     * @param description   The description of the event.
     * @param isDone        The event is marked as done.
     * @param at            The date of the event.
     * @param time          The time of the event.
     */
    public Event(final String description, final boolean isDone,
                 final LocalDate at, final String time) {
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
                    + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + time + ")";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at
                + (time == null || time.isEmpty() ? "" : " " + time)
                + ")";
    }
}
