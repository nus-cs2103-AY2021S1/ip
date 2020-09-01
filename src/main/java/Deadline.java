import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task object with an added by parameter to indicate when the task is due.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    /**
     * Creates a new Deadline object with the given description and a String by representing when it is due.
     * @param description Description of the task that needs to be performed
     * @param by String representation of when the task is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new Deadline object with the given description, a String by representing when it is due and
     * whether it has been completed.
     * @param description Description of the task that needs to be performed
     * @param by String representation of when the task is due
     * @param isDone Completion status of the task
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Creates a new Deadline object with the given description and the due date in LocalDate format.
     * @param description Description of the task that needs to be performed
     * @param byDate LocalDate representation of when the task is due
     */
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
