import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task object with an added by parameter to indicate when the task is due.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        String output = "[D]" + super.toString() + " (by: ";
        if (this.byDate == null) {
            output += this.by;
        } else {
            output += this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return output + ")";
    }

    @Override
    public String getSaveFormat() {
        return "D | " + super.getSaveFormat() + " | " + this.by;
    }
}
