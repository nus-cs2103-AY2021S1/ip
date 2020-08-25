import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline. A deadline has both a description and a date/time indicating
 * when the task must be completed.
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a String representation of the Deadline that will be saved in the
     * hard disk.
     * @return a String representation of the Deadline.
     */
    public String formattedString() {
        return "D | " + (super.isDone? 1 : 0) + " | " + super.description + " | " +
                by;
    }
}
