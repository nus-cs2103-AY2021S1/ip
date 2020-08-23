import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected String time;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.time = null;
    }

    public Deadline(String description, LocalDate by, String time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
        this.time = null;
    }

    public Deadline(String description, boolean isDone, LocalDate by, String time) {
        super(description, isDone);
        this.by = by;
        this.time = time;
    }

    public String display() { //format time here
        if (time == null || time.isEmpty()) {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + time + ")";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by + (time == null || time.isEmpty() ? "" : time) + ")";
    }
}