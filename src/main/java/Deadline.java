import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that has a description, a date, and a state of whether it has been done.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a new deadline task.
     * @param description the description of the deadline task
     * @param by the date by when the task should have been finished
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        assert by != null;
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        assert by != null;
        return "D " + super.toSave() + "/" + by;
    }
}
