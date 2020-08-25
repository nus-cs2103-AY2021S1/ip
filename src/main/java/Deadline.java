import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        return "D" + " | " + completionStatus + " | " + this.description + " | "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ")";
    }
}