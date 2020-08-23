import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final String dateFormat = "yyyy-MM-dd HH:mm";
    protected LocalDateTime at;

    public Event(String details, String at) throws DukeException {
        super(details);
        validateDateTime(at);
    }

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
            this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
