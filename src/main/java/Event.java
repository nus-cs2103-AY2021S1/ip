import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event to be completed.
 */
public class Event extends Task {
    private static final String dateFormat = "yyyy-MM-dd HH:mm";
    protected LocalDateTime at;

    /**
     * Constructor for an event.
     * @param details event details.
     * @param at date and time.
     * @throws DukeException If an invalid date or time is provided.
     */
    public Event(String details, String at) throws DukeException {
        super(details);
        validateDateTime(at);
    }

    /**
     * Helper method for constructor.
     * @param dateTime date and time.
     * @throws DukeException If invalid date/time given.
     */
    public void validateDateTime(String dateTime) throws DukeException {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
            at = LocalDateTime.parse(dateTime, df);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter a valid date&time in this format: yyyy-MM-dd HH:mm");
        }
    }

    public LocalDateTime getDateTime() {
        return this.at;
    }

    /**
     * Returns a string representation of the event.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
            this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
