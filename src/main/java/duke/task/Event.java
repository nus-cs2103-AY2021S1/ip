package duke.task;

import duke.component.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An event that has a specific time.
 */
public class Event extends Task {

    protected LocalDate atDate;

    /**
     * Initializes an Event using the given description and event date
     * @param description the description of the event
     * @param atDate the date of the event
     * @throws DukeException if the input command is invalid
     */
    public Event(String description, String atDate) throws DukeException {
        super(description);
        try {
            this.atDate = LocalDate.parse(atDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date detected! Please enter date as yyyy-mm-dd.");
        }
    }

    /**
     * Returns the String representation of the event
     * @return the String representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of the event when it is stored in a data file
     * @return the String representation of the event when it is stored in a data file
     */
    @Override
    public String toStorageString() {
        if (super.isDone) return "E | 1 | " + description + " | " + atDate;
        else return "E | 0 | " + description + " | " + atDate;
    }

}
