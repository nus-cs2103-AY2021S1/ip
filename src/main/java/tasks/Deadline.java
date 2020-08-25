package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Returns a deadline task.
     *
     * @param description description of the deadline.
     * @param by the date and time of the deadline.
     * @see Task
     */
    public Deadline(String description, LocalDateTime by){
        super(description);
        this.by = by;
    }

    /**
     * Returns the date and time of the deadline.
     *
     * @return LocalDateTime.
     * @see LocalDateTime
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy h.mm a");
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }
}
