package duke.tasks;

import java.time.LocalDate;

import duke.timeformatter.TimeFormatter;

/**
 * Represents a Task that has an associated Deadline.
 */
public class Deadline extends Task {

    /**
     * @param description Description of the Deadline.
     * @param by Date associated with the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    /**
     * Returns the String representation of this deadline to be displayed to the user.
     *
     * @return The String representation of this deadline to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + super.toString() + " [by: "
                + TimeFormatter.prettyDate(localDate) + "]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Deadline;
    }
}
