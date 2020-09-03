package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents a deadline that extends from the Task class, which consists of a description and a due date.
 */
public class Deadline extends Task {
    private String deadlineDate;
    private LocalDate deadlineLocalDate;
    private String formatDeadlineDate;

    /**
     * Constructs a new Deadline object.
     * @param taskName name of the Deadline.
     * @param deadlineDate date which the Deadline is due.
     * @throws DukeException if the date provided is of the wrong format.
     */
    public Deadline(String taskName, String deadlineDate) throws DukeException {
        super(taskName, "D");
        try {
            this.deadlineDate = deadlineDate;
            this.deadlineLocalDate = LocalDate.parse(deadlineDate);
            this.formatDeadlineDate = this.deadlineLocalDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (Exception e) {
            throw new DukeException("Please use YYYY-MM-DD format for dates~");
        }
    }

    /**
     * Gets the due date of the Deadline.
     * @return a string representing a due date in DD-MMM-YYYY format.
     */
    public String getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName + " (by:" + formatDeadlineDate + ")";
    }
}

