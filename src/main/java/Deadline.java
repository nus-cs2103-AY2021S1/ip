import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// a task that need to specify a date to be done before the date
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma")) + ")";
    }
}