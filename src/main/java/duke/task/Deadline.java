package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Deadline class encapsulates information and methods pertaining to a
 * Deadline.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Creates a Deadline with the given details and date.
     * The Deadline is set as not done.
     *
     * @param details Details of deadline.
     * @param date Date of the deadline.
     */
    public Deadline(String details, LocalDateTime date) {
        super(details);
        this.date = date;
    }

    /**
     * Creates a Deadline with the given details and date.
     * The Deadline is set as done if isDone is true.
     *
     * @param details Details of the deadline.
     * @param isDone True if the deadline is done.
     * @param date Date of the deadline.
     */
    public Deadline(String details, boolean isDone, LocalDateTime date) {
        super(details, isDone);
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline to be saved in hard disk.
     *
     * @return string representation of the Deadline
     */
    @Override
    public String store() {
        return "D " + super.store() + " /by " + this.date;
    }

    /**
     * Returns a string representation of the Deadline to be printed.
     *
     * @return string representation of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)) + ")";
    }
}
