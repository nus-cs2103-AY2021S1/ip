package duke;

import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidUpdateInputException;

/**
 * Represents a Event task
 */
public class Event extends Task {

    /**
     * Initialise a Event object
     * @param description  Description of deadline
     * @param date         Date of event
     * @param time         Time of event
     * @throws InvalidDateTimeException
     */
    public Event(String description, String date, String time) throws InvalidDateTimeException,
            InvalidUpdateInputException {
        super(description);
        this.setDateTime(date, time);
    }

    /**
     * Convert Event object to a string representation.
     * @return  String value to be stored in file
     */
    @Override
    public String toData() {
        return checkIsDone()
                ? "E//1//" + getDescription() + "//" + getDateTime().getDate() + "//" + getDateTime().getTime()
                : "E//0//" + getDescription() + "//" + getDateTime().getDate() + "//" + getDateTime().getTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}
