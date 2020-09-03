import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to handle the Event task type
 */
public class Event extends Task {
    protected LocalDateTime atTime;

    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        try {
            this.atTime = LocalDateTime.parse(at, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Cannot parse event time.");
        }
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        try {
            this.atTime = LocalDateTime.parse(at, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Cannot parse event time, time set as null.");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.atTime);
    }
}
