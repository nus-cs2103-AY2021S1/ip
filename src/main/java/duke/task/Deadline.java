package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class contains the name, completion status
 * and deadline of a task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for a Deadline object.
     *
     * @param name The name of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns the deadline of a Deadline object.
     *
     * @return A LocalDate which represents the deadline of the task.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string representation of a Deadline object.
     *
     * @return A String which contains the name, completion
     * status and deadline of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}