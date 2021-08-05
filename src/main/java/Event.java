import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
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

//    /**
//     * Event constructor with date only.
//     *
//     * @param desc   The description of the event.
//     * @param at            The date of the event.
//     */
//    public Event(final String desc, final LocalDate at) {
//        super(desc);
//        this.at = at;
//    }
//
//    /**
//     * Event constructor with date and time.
//     *
//     * @param desc          The description of the event.
//     * @param at            The date of the event.
//     * @param time          The time of the event.
//     */
//    public Event(final String desc, final LocalDate at,
//                 final String time) {
//        super(desc);
//        this.at = at;
//        this.time = time;
//    }
//
//    /**
//     * Event constructor with date and marked as done.
//     *
//     * @param desc   The description of the event.
//     * @param isDone        The event is marked as done.
//     * @param at            The date of the event.
//     */
//    public Event(final String desc, final boolean isDone,
//                 final LocalDate at) {
//        super(desc, isDone);
//        this.at = at;
//        this.time = null;
//    }

    /**
     * Event constructor with date and time and marked as done.
     *
     * @param desc          The description of the event.
     * @param isDone        The event is marked as done.
     * @param at            The date of the event.
     * @param time          The time of the event.
     */
    public Event(final String desc, final boolean isDone,
                 final LocalDate at, final String time, final Tag tag) {
        super(desc, isDone, tag);
        this.at = at;
        this.time = time;
    }

    /**
     * Formats the task for display to the user.
     *
     * @return The string containing the task details.
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
