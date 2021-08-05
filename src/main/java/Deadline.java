import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * @author Siqi
 * @version 1.`
 * @since 2020-09-08
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

//    /**
//     * Deadline constructor with date only.
//     *
//     * @param desc   The description of the deadline task.
//     * @param by     The date that the deadline task is due.
//     */
//    public Deadline(final String desc, final LocalDate by) {
//        super(desc);
//        this.by = by;
//        this.time = null;
//    }
//
//    /**
//     * Deadline constructor with date and time.
//     *
//     * @param desc  The description for the deadline task.
//     * @param by    The date that the deadline task is due.
//     * @param time  The time that the deadline task is due.
//     */
//    public Deadline(final String desc, final LocalDate by,
//                    final String time) {
//        super(desc);
//        this.by = by;
//        this.time = time;
//    }
//
//    /**
//     * Deadline constructor with date and marked as done.
//     *
//     * @param desc          The description of the deadline task.
//     * @param isDone        The deadline task is marked as done.
//     * @param by            The date that the deadline task is due.
//     */
//    public Deadline(final String desc, final boolean isDone,
//                    final LocalDate by) {
//        super(desc, isDone);
//        this.by = by;
//        this.time = null;
//    }

    /**
     * Deadline constructor with date and time and marked as done.
     *
     * @param desc          The description of the deadline task.
     * @param isDone        The deadline task is marked as done.
     * @param by            The date that the deadline task is due.
     * @param time          The time that the deadline task is due.
     */
    public Deadline(final String desc, final boolean isDone,
                    final LocalDate by, final String time, final Tag tag) {
        super(desc, isDone, tag);
        this.by = by;
        this.time = time;
    }

    /**
     * Formats the task for display to the user.
     *
     * @return The string containing the task details.
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
