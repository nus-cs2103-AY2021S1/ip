import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getDueDate() {
        return this.by;
    }

    /**
     * Returns the format for permanent storage of a deadline task in file.
     * @return string format for storing.
     */
    @Override
    public String getStoringFormat() {
        return "D " + super.getStoringFormat() + " ~ " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
