import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    Deadline(String description, LocalDate by) {
        this(description, false, by);
    }

    Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Return updated task of subtype: Deadline
     *
     * @param isDone New status for the task
     * @return new Deadline with updated status
     */
    @Override
    public Task updateStatus(boolean isDone) {
        return new Deadline(super.description, isDone, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}