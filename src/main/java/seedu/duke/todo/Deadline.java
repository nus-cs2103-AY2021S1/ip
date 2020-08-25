package main.java.seedu.duke.todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which is due by a particular day.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the date of the deadline.
     * @return the date of the deadline.
     */
    @Override
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the data representation of the deadline for storage.
     * @return the data representation of the deadline.
     */
    @Override
    public String getData() {
        return "D " + super.getData() + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
