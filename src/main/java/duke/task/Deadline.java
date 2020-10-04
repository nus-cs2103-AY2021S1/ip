package duke.task;

import duke.exception.DukeException;
import duke.util.DateTime;

/**
 * Class representing a deadline.
 */
public class Deadline extends Task {

    /**
     * Creates a brand new {@code Deadline}.
     *
     * @param description Description of the deadline.
     * @param by Time that the deadline is due by.
     */
    public Deadline(String description, DateTime by) {
        super(description);
        this.dateTime = by;
        this.taskType = "D";
    }

    private Deadline(String[] data) throws DukeException {
        super(data);
    }

    /**
     * Loads and initializes a {@code Deadline} with pre-existing data.
     *
     * @param data Deadline data.
     * @return A Deadline task containing the data provided.
     * @throws DukeException If data provided is of an invalid form.
     */
    public static Deadline loadFromData(String[] data) throws DukeException {
        return new Deadline(data);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDateTime());
    }
}
