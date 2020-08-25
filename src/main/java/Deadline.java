import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class that represents a Task with a deadline.
 * It has a Task description, as well as a deadline date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the representative text of the Deadline.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "D";
        return type + "/" + super.taskSaver() + "/" + by.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Outputs the Deadline as a String.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
