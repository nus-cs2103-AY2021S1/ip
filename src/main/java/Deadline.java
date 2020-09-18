import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Deadline object.
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public LocalDate getDate() {
        return by;
    }

    /**
     * Saves whether the task is done or not into storage.
     * @return a string representation of the task.
     */
    @Override
    public String saveTask() {
        return "D" + super.saveTask() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}