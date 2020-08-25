package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task, where a date must be specified
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline object with the specified <code>description</code> and
     * <code>by</code>.
     *
     * @param description Description of the task.
     * @param by Date by which this task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        LocalDate d = LocalDate.parse(by);
        String reformattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + reformattedDate + ")";
    }
}
