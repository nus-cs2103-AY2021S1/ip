package duke.tasks;

import duke.timeformatter.TimeFormatter;

import java.time.LocalDate;

/**
 * Represent a task that specifies a deadline
 */
public class Deadline extends Task {

    /**
     * Deadline of the deadline task.
     */
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + super.toString() + "(by: " + TimeFormatter.prettyDate(by) + ")";
    }
}
