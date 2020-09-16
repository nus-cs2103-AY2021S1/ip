package duke.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline type of Task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline-type Tasks.
     *
     * @param description Description of task input by user.
     * @param by Date by which task must be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline-type tasks loaded from memory.
     *
     * @param description Description of task.
     * @param isDone Current state of task.
     * @param by Date by which task must be completed.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super (description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Generates String with relevant dat to save to disk.
     *
     * @return String that contains data to save.
     */
    public String toDataString() {
        return String.format("D|%s|%s|%s", super.isDone, super.description, this.by);
    }

    public LocalDate getBy() {
        return by;
    }
}
