package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline as a subclass of Task that handles a deadline task.
 */

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }
    public Deadline(String description, String by, boolean done) {
        super(description, done);
        this.by = LocalDate.parse(by);
    }

    /**
     * convert a Task string to a format that is "[task] [done/not done] /by [date]"
     * @return a formatted string to be stored in a file
     */
    @Override
    public String inputStyle() {
        return "deadline " + super.inputStyle() + "/by " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
