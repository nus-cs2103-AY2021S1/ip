package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a Deadline, a type of Task, in the Duke program.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a Deadline.
     * @param description the description of the deadline
     * @param date the date of the deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.by = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
            + " (by: " + by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}
