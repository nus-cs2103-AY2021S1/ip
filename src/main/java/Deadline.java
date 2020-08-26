import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime localDateTime;

    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    public Deadline(String description, boolean isDone, LocalDateTime localDateTime) {
        super(description, isDone);
        this.localDateTime = localDateTime;
    }

    String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyy, hh:mm a");
        return this.localDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime() + ")";
    }
}
