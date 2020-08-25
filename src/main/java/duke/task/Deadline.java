package duke.task;

import duke.component.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task that has a deadline
 */
public class Deadline extends Task {

    protected LocalDate byDate;

    /**
     * Initializes a Deadline using the given description and due date
     * @param description the description of the deadline
     * @param byDate the due date of the deadline
     * @throws DukeException if the input command is invalid
     */
    public Deadline(String description, String byDate) throws DukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date detected! Please enter date as yyyy-mm-dd.");
        }
    }

    /**
     * Returns the String representation of the deadline
     * @return the String representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of the deadline when it is stored in a data file
     * @return the String representation of the deadline when it is stored in a data file
     */
    @Override
    public String toStorageString() {
        if (super.isDone) return "D | 1 | " + description + " | " + byDate;
        else return "D | 0 | " + description + " | " + byDate;
    }
}