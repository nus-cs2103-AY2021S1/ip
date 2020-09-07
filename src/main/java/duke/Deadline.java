package duke;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class used for deadline tasks.
 */
class Deadline extends Task {

    /**
     *  String used to store deadline.
     */
    protected LocalDate deadlineDate;

    /**
     * Constructor for deadline class.
     *
     * @param description Task description.
     * @param deadlineDate Deadline.
     */
    Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructor for deadline class.
     *
     * @param description Task description.
     * @param deadlineDate Deadline.
     * @param isDone Describes if task is completed.
     */
    Deadline(String description, LocalDate deadlineDate, boolean isDone) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    @Override
    String toStringForStorage() {
        return "D | " + super.toStringForStorage() + " | " + deadlineDate;
    }

    @Override
    public String toString() {
        String formattedDate = this.deadlineDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
