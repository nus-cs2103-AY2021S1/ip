import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. A deadline task has a description and the deadline of the task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Marks a deadline task as done.
     *
     * @return A deadline task that is done.
     */
    @Override
    public Deadline markAsDone() {
        Deadline doneDeadline = new Deadline(this.description, this.by, true);
        return doneDeadline;
    }

    @Override
    public String toTxtFileFormat() {
        return "D" + super.toTxtFileFormat() + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }
}