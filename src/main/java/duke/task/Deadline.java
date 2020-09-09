package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a deadline task with description, a date to complete the task by, and not done by default.
     * @param description Description of task.
     * @param by Date to complete the task by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task with description, done status, a date to complete the task by.
     * @param description Description of the task.
     * @param isDone Done status.
     * @param by Date to complete the task by.
     */
    protected Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    protected void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    protected void updateTaskTime(String newTime) throws YooException {
        try {
            this.by = LocalDate.parse(newTime);
        } catch (DateTimeParseException e) {
            throw new YooException("Date format should be yyyy-mm-dd!\nPlease try again (>_<)");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
