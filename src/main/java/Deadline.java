import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String dateFormat = "yyyy-MM-dd HH:mm";
    protected LocalDateTime by;

    public Deadline(String details, String by) throws DukeException {
        super(details);
        validateDateTime(by);
    }

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
            this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
