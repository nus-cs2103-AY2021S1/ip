package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline. A type of Task.
 */
public class Deadline extends Task {

    /**
     * Creates a Deadline object with the specified description
     * and deadline.
     * @param description Description of the Deadline.
     * @param by Time of the Deadline.
     * @throws DateTimeParseException If the input time is of the wrong format.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDateTime.parse(by);
        this.type = "D";
    }

    private Deadline(String desc, String by, boolean isDone) throws DateTimeParseException {
        super(desc, isDone);
        this.deadline = LocalDateTime.parse(by);
        this.type = "D";
    }

    @Override
    public Deadline taskDone() {
        return new Deadline(this.getDescription(), this.getDeadline(), true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDeadline("MMM d yyyy") + ")";
    }

}
