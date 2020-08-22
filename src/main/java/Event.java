import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    LocalDate eventTime;

    Event(String taskName, String eventTime) throws DukeException {
        super(taskName);
        // ISO_LOCAL_DATE format, no conversion needed (yyyy-mm-dd)
        try {
            this.eventTime = LocalDate.parse(eventTime.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write down our dates in the standard format, yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[EVENT]" + super.toString() + " | at: " + eventTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
