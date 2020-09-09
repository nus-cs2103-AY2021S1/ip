package duke.tasks;

import java.time.LocalDate;

import duke.timeformatter.TimeFormatter;

/**
 * Represents a task that specifies a deadline
 */
public class Deadline extends Task {

    /**
     * @param description description of the deadline task.
     * @param by          date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + super.toString() + "(by: "
                + TimeFormatter.prettyDate(localDate) + ")";
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }
}
