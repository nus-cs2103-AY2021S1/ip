package duke.tasks;

import duke.util.DukeDate;
import duke.util.DukeException;

/**
 * Deadline Task object with description and due date.
 */
public class DeadlineTask extends Task {
    protected DukeDate due;

    /**
     * Constructor.
     * @param description string description of event.
     * @param due string due time/date of event.
     * @throws DukeException
     */
    public DeadlineTask(String description, String due) throws DukeException {
        super(description);
        this.due = new DukeDate(due);
    }

    /**
     * Overloaded Constructor to create existing tasks in storage.
     * @param description string description of event.
     * @param due string due time/date of event.
     * @param isCompleted boolean check if task has been completed.
     * @throws DukeException
     */
    public DeadlineTask(String description, String due, boolean isCompleted) throws DukeException {
        super(description);
        this.due = new DukeDate(due);
        this.isCompleted = isCompleted;
    }

    /**
     * Displays the string output representation in storage.
     * @return string representation.
     */
    @Override
    public String fileString() {return "D | " + super.fileString() + " | " + due; }

    /**
     * Displays the string output representation.
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }
}
