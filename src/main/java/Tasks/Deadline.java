package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Create Deadline objects extends from Task class.
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Create a new Deadline object with isDone equal true.
     *
     * @return new Deadline object.
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

