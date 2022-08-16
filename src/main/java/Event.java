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
        try {
            this.atTime = LocalDateTime.parse(at, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                this.atTime = LocalDateTime.parse(at, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Cannot parse event time:");
                System.out.println(at);
            }
        }
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        try {
            this.atTime = LocalDateTime.parse(at, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                this.atTime = LocalDateTime.parse(at, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Cannot parse event time:");
                System.out.println(at);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.atTime);
    }
}
