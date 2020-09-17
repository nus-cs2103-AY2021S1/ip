package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline as a subclass of Task that handles a deadline task added by user.
 */

public class Deadline extends Task {

    private static final DateTimeFormatter MMM_D_YYYY = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate by;

    /**
     * Constructor for a deadline event.
     * @param description of the event.
     * @param by deadline specified.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Overloaded constructor for a deadline event with a description.
     * @param description of the event.
     * @param by deadline specified.
     * @param isDone of the deadline event.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * convert a Task string to a format that is "[task] [done/not done] /by [date]".
     * @return a formatted string to be stored in a file.
     */
    @Override
    public String inputStyle() {
        return "deadline " + super.inputStyle()
                + "/by "
                + by
                + " #" + super.priority;
    }

    @Override
    public String toString() {
        if (super.priority == Priority.HIGH) {
            return "#" + super.priority.toString() + " [D]" + super.toString() + " (by: "
                    + by.format(MMM_D_YYYY) + ")";
        } else {
            return "[E]" + super.toString() + " (by: "
                    + by.format(MMM_D_YYYY) + ")";
        }
    }
}
