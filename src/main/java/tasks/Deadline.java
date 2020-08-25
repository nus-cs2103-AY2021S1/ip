package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by){
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy h.mm a");
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }
}
