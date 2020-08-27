package duke.task;

import duke.DukeException;
import duke.util.DateTimeHandler;

/**
 * Represents a deadline as a task with a date and time.
 */
public class Deadline extends Task {

    private final String dateTime;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.dateTime = DateTimeHandler.parseDateTime(by);
    }

    public Deadline(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String getData() {
        return "D / " + super.getData() + " / " + this.getDateTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateTime() + ")";
    }
}
