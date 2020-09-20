package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates Deadline object.
     * @param description String description to describe Deadline.
     * @param by LocalDate to describe when Deadline occurs.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates Event object.
     * @param description String description to describe Deadline.
     * @param by LocalDate to describe when Deadline occurs.
     * @param isDone Boolean to indicate completion status of Deadline.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Creates Event object.
     * @param description String description to describe Deadline.
     * @param by LocalDate to describe when Deadline occurs.
     * @param isDone Boolean to indicate completion status of Deadline.
     * @param tag String to tag Event.
     */
    public Deadline(String description, LocalDate by, boolean isDone, String tag) {
        super(description, isDone, tag);
        this.by = by;
    }

    /**
     * Returns description of this task and its completion status, with its deadline date.
     * @return String that describes task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
