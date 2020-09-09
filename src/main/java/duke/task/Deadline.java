package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline
     *
     * @param description description of deadline task.
     * @param by          due date of deadline task (in dd-mm-yyyy format).
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline
     *
     * @param description description of deadline task.
     * @param isDone      whether or not a task has been done.
     * @param by          due date of deadline task (in dd-mm-yyyy format).
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    /**
     * Returns task printing with proper formatting.
     *
     * @return String with task formatting
     */
    public String saveText() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
