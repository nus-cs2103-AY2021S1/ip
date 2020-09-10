package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a Deadline.
     *
     * @param description description of deadline
     * @param by date and time of deadline
     */
    public Deadline(String description, LocalDateTime by, String tag) {
        super(description, tag);
        this.by = by;
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        if (hasTag) {
            return "D" + " | " + completionStatus + " | " + this.description + " | "
                    + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HHmm"))
                    + tag.toString();
        } else {
            return "D" + " | " + completionStatus + " | " + this.description + " | "
                    + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HHmm"));
        }
    }

    @Override
    public String toString() {
        if (hasTag) {
            return "[D]" + this.getStatusIcon() + " " + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ")"
                    + tag.toString();
        } else {
            return "[D]" + this.getStatusIcon() + " " + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ")";
        }

    }
}
