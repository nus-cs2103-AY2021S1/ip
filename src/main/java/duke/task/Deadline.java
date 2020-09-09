package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    public Tag tag;

    /**
     * Creates a Deadline.
     *
     * @param description description of deadline
     * @param by date and time of deadline
     */
    public Deadline(String description, LocalDateTime by, boolean hasTag) {
        super(description, hasTag);
        this.by = by;
        if (hasTag) {
            this.tag = new Tag(description.substring(description.indexOf("@") + 1));
        }
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        return "D" + " | " + completionStatus + " | " + this.description + " | "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HHmm"))
                + " @" + tag.toString();
    }

    @Override
    public String toString() {
        if (hasTag) {
            return "[D]" + this.getStatusIcon() + " " + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ") @"
                    + tag.toString();
        } else {
            return "[D]" + this.getStatusIcon() + " " + super.toString() + " (by: "
                    + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ")";
        }

    }
}
