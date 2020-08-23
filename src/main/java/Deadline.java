import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline to be completed.
 */
public class Deadline extends Task {
    private static final String dateFormat = "yyyy-MM-dd HH:mm";
    protected LocalDateTime by;

    /**
     * Constructor for the deadline.
     * @param details deadline details.
     * @param by date and time to be completed.
     * @throws DukeException If an invalid date or time is provided.
     */
    public Deadline(String details, String by) throws DukeException {
        super(details);
        validateDateTime(by);
    }

    /**
     * Helper method for constructor.
     * @param dateTime date and time.
     * @throws DukeException If invalid date/time given.
     */
    public void validateDateTime(String dateTime) throws DukeException {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
            by = LocalDateTime.parse(dateTime, df);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter a valid date&time in this format: yyyy-MM-dd HH:mm");
        }
    }

    public LocalDateTime getDateTime() {
        return this.by;
    }

    /**
     * Returns the string representation of the deadline.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
            this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
