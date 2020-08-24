import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected String byString;
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.byString = by.format(DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm"));
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}