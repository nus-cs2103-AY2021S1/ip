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

    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
