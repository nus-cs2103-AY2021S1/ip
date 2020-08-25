package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a deadline specified.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Deadline constructor.
     *
     * @param description description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Deadline constructor.
     *
     * @param description description of the task.
     * @param by the deadline of the task.
     * @param isDone specify whether the task is done or not.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    private String dateFormat() {
        return by.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateFormat() + ")";
    }

    /**
     * Format task to be written to a file.
     *
     * @return formatted string of the task.
     */
    public String writeToFile() {
        return String.format("D | %b | %s | %s", this.isDone, this.description, this.by);
    }
}
