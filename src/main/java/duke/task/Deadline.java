package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task that has a deadline.
 * The deadline is denoted by a LocalDateTime object.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a deadline task object with the given description and deadline.
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task object that has been completed on the given done date
     * with the given description and deadline.
     * @param description Description of the task.
     * @param by Deadline of the task.
     * @param doneDate Date of completion.
     */
    public Deadline(String description, LocalDateTime by, LocalDate doneDate) {
        super(description, doneDate);
        this.by = by;
    }

    @Override
    public String toData() {
        return "D | " + super.toData() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + "h)";
    }
}
