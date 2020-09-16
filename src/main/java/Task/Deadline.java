package Task;

import java.time.LocalDate;

/**
 * Represents a task to be completed by a certain time.
 */
public class Deadline extends Task {

    /**
     * @param description Task.Task description.
     * @param date Task.Deadline for the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description, date);
    }

    /**
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + ")\n";
    }
}