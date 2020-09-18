package duke;

import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidUpdateInputException;

/**
 * Represents a Deadline task
 */
public class Deadline extends Task {

    /**
     * Initialise a deadline object
     * @param description  Description of deadline
     * @param date         Date of event
     * @param time         Time of event
     * @throws InvalidDateTimeException
     */
    public Deadline(String description, String date, String time) throws InvalidDateTimeException,
            InvalidUpdateInputException {
        super(description);
        this.setDateTime(date, time);
    }



    /**
     * Convert Deadline object to a string representation.
     * @return  String value to be stored in file
     */
    @Override
    public String toData() {
        return checkIsDone()
                ? "D//1//" + getDescription() + "//" + getDateTime().getDate() + "//" + getDateTime().getTime()
                : "D//0//" + getDescription() + "//" + getDateTime().getDate() + "//" + getDateTime().getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}
