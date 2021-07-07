package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a type of task called deadline which has a due time.
 */
public class Deadline extends Task {
    protected String deadline;
    protected LocalDateTime dateTime;

    public Deadline(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
        this.dateTime = null;
    }

    /**
     * Updates the due time with the user input if it fits the required
     * format of yyyy/MM/dd HHmm.
     *
     * @throws DukeException If the format of the input is not yyyy/MM/dd HHmm.
     */
    protected void updateDateTime() throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
            if (this.deadline.length() == 15) {
                this.dateTime = LocalDateTime.parse(this.deadline, formatter);
            }
        } catch (Exception ex) {
            throw new DukeException("The format for a deadline with specific date" +
                    "and time should be yyyy/MM/dd HHmm.");
        }
    }

    /**
     * Returns the string representation of a deadline
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        if (this.dateTime == null) {
            return "[D]" + super.toString() + " (by: " + this.deadline + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        }
        
    }
}
