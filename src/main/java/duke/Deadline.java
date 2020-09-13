package duke;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class used for deadline tasks.
 */
class Deadline extends Task {

    /** String used to store deadline. */
    protected LocalDate deadlineDate;

    /**
     * Constructs deadline.
     *
     * @param description Task description.
     * @param deadlineDate Deadline.
     */
    Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructs deadline.
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
     * Returns string format for file storage.
     *
     * @return String description.
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
