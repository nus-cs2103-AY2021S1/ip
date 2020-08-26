import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter D_DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMM dd uuuu, ha");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String getDeadlineDateTime() {
        return by.format(D_DATETIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineDateTime() + ")";
    }
}
