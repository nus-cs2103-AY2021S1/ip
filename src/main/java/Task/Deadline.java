package Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        if (this.byDate == null) {
            return "[D]" + icon + " " + this.description + " (by: " + this.by + ")";
        } else {
            return "[D]" + icon + " " + this.description + " (by: "
                    + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
        }
    }
}
