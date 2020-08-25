package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    /**
     * Deadline of task.
     */
    public LocalDateTime deadline;

    /**
     * Constructs a new instance of an DeadlineTask.
     * @param description Description of deadline task.
     * @param deadline Deadline of deadline task.
     * @throws DukeException If deadline is not in the form of "dd-MM-yyyy HH:mm".
     */
    public DeadlineTask(String description, String deadline) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException exception) {
            System.out.println(deadline);
            throw new DukeException("Error! Invalid date format, Please enter the date in the format dd-MM-yyyy HH:mm");
    }

    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }

}
