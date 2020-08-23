package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task with a deadline date.
 */
public class Deadline extends Task {
    LocalDate deadlineTime;

    /**
     * Constructs a Deadline with name and deadline date.
     * @param taskName name of the Deadline.
     * @param deadlineTime date of the Deadline.
     * @throws DukeException If name is empty OR date is in wrong format.
     */
    public Deadline(String taskName, String deadlineTime) throws DukeException {
        super (taskName);
        // ISO_LOCAL_DATE format, no conversion needed (yyyy-mm-dd)
        try {
            this.deadlineTime = LocalDate.parse(deadlineTime.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write down our dates in the standard format, yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[DEADLINE] " + super.toString() + " | by: " + deadlineTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
