import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. A deadline task has a description and the deadline of the task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a deadline task that is not done.
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task, specifying if it is done
     * @param description Description of the task.
     * @param by Deadline of the task.
     * @param isDone True to show task is done, False to show task is not done.
     */
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
