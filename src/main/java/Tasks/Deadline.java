package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task{

    /** The time that the deadline has to be done by */
    protected LocalDate by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = LocalDate.parse(by);
    }

    /**
     * Gets the date of the deadline in the format yyyy-mm-dd
     * @return the date of the deadline in the format yyyy-mm-dd
     */
    public LocalDate getByDate() {
        return this.by;
    }

    /**
     * Gets the date of the deadline in the format mmm d yyyy
     * @return the date of the deadline in the format mmm d yyyy
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Overriden toString method for deadline class
     * @return the string representation for deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
