import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime byTime;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        try {
            this.byTime = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Cannot parse deadline time.");
        }
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        try {
            this.byTime = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Cannot parse deadline time, time set as null.");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.byTime);
    }
}
