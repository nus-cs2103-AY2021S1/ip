package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;


/**
 * Represents a Deadline object that has a task description and a deadline timing.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor to create a deadline object.
     *
     * @param detail is the description entered by the user.
     * @param deadline is the time for the deadline entered by the user.
     * @throws DukeException if the detail entered is invalid.
     */
    public Deadline(String detail, LocalDateTime deadline) throws DukeException {
        super(detail);
        this.deadline = deadline;
    }

    /**
     * Constructor that is overloaded to create a deadline object from the database.
     *
     * @param doneStatus the state of the task from the user's previous session
     * @param detail the description of the deadline
     * @param deadline the time of the deadline.
     */
    public Deadline(int doneStatus, String detail, LocalDateTime deadline) {
        super(doneStatus, detail);
        this.deadline = deadline;
    }

    @Override
    public String formatTaskForDatabase() {
        int status = super.getDoneStatus() ? 1 : 0;
        return "D|" + status + "|" + super.description + "|" + this.deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " " + super.description + " (by:"
                + deadline.format(dateTimeFormatter) + ")";
    }
}
