package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Item in the TaskList.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a new Deadline item.
     * @param description of Deadline.
     * @param by Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a {@code String} to be written into saved data.
     */
    @Override
    public String storageForm() {
        return "D" + ", "  + (isDone ? "1" : "0") + ", " + description + ", " + by.toString();
    }

    @Override
    public String getType() { return "Deadline"; }

    @Override
    public LocalDate getDate() { return this.by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}