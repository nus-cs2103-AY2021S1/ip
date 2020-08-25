import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Initialise a deadline.
     * @param desc contains description.
     * @param by contains deadline.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    @Override
    public String toString() {
        if (date != null) {
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + by + ")";
        }
    }
}
