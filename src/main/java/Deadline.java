import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to handle the Deadline task type
 */
public class Deadline extends Task {
    protected LocalDateTime byTime;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        try {
            this.byTime = LocalDateTime.parse(by, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                this.byTime = LocalDateTime.parse(by, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Cannot parse event time:");
                System.out.println(by);
            }
        }
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.byTime = LocalDateTime.parse(by, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                this.byTime = LocalDateTime.parse(by, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Cannot parse event time:");
                System.out.println(by);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.byTime);
    }
}
