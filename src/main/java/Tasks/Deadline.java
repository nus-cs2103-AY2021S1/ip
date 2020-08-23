package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Create Deadline objects extends from Task class.
 */
public class Deadline extends Task {
    /** Deadline. */
    protected LocalDate by;

    /**
     * Constructs Deadline object with description and date given.
     *
     * @param description Deadline's description.
     * @param by Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs Deadline object with deadline's description, date and deadline status given.
     *
     * @param description Deadline's description.
     * @param by Deadline.
     * @param isDone Deadline status.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Creates a new Deadline object with isDone equal true.
     *
     * @return New Deadline object.
     */
    @Override
    protected Deadline markAsDone() {
        return new Deadline(super.description, this.by, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " [" + by.getDayOfWeek() + "])";
    }
}

