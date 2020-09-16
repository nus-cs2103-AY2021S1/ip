package gel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of <code>Task</code> with a date and time description of when the task is due.
 */
public class Deadline extends Task {

    protected String byString;
    protected LocalDateTime by;

    /**
     * Construct a Deadline task.
     *
     * @param description Description of task.
     * @param by Deadline of task. in LocalDateTime format.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.byString = by.format(DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm"));
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
