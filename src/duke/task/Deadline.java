package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a Deadline which is a Task which needs to be done before a certain Date.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Creates a Deadline instance containing a description and a Date.
     *
     * @param description Description of Deadline to be done.
     * @param by Date by which the Deadline should be done latest by.
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    /**
     * Creates a Deadline instance containing a description and a Date.
     *
     * @param description Description of Deadline to be done.
     * @param by Date by which the Deadline should be done latest by.
     * @param isDone True if the Deadline is already done otherwise false.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Format in which the Deadline should be saved in Storage.
     *
     * @return String format of the Deadline for saving in Storage.
     */
    @Override
    public String saveFormat() {
        return "D" + "~" + super.saveFormat() + "~" + this.by;
    }

    /**
     * Checks whether the Deadline has the same Date as specified.
     *
     * @param theDate Date to check whether the Deadline has occurred on.
     * @return True if the Deadline occurred on the specified date otherwise false.
     */
    @Override
    public boolean hasSameDate(LocalDate theDate) {
        return by.toLocalDate().equals(theDate);
    }

    /**
     * Returns the String representation of the Deadline to be displayed to the user.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(this.DATE_FORMAT) + ")";
    }
}