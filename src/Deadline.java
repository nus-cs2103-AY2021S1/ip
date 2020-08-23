import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    public String saveText() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
