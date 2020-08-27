import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class used for deadline tasks.
 */
public class Deadline extends Task {

    /**
     *  String used to store deadline.
     */
    protected LocalDate by;

    /**
     * Constructor for deadline class.
     *
     * @param description Task description.
     * @param by Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for deadline class.
     *
     * @param description Task description.
     * @param by Deadline.
     * @param isDone Describes if task is completed.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    @Override
    public String toStringSimple() {
        return "D | " + super.toStringSimple() + " | " + by;
    }

    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
