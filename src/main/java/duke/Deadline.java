package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with description and deadline.
     * @param description Description of Deadline task.
     * @param by Deadline of task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with description, status and deadline.
     * @param description Description of Deadline task.
     * @param isDone Status of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns deadline of task.
     * @return String representation of deadline.
     */
    public String getDate() {
        return by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
