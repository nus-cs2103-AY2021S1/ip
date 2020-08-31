package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline as a subclass of Task that handles a deadline task.
 */

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for a deadline event.
     * @param description of the event
     * @param by deadline speicified
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Overloaded constructor for a deadline event with a description.
     * @param description of the event
     * @param by deadline specified
     * @param isDone of the deadline event
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * convert a Task string to a format that is "[task] [done/not done] /by [date]"
     * @return a formatted string to be stored in a file
     */
    @Override
    public String inputStyle() {
        return "deadline " + super.inputStyle()
                + "/by "
                + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
