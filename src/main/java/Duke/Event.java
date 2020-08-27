package Duke;

import Duke.Exception.*;

/**
 * Represents a Event task
 */
public class Event extends Task {

    private final DateAndTime datetime;

    /**
     * Initialise a Event object
     * @param description  Description of deadline
     * @param date         Date of event
     * @param time         Time of event
     * @throws InvalidDateTimeException
     */
    public Event(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.datetime = new DateAndTime(date, time);
    }

    /**
     * Convert Event object to a string representation.
     * @return  String value to be stored in file
     */
    @Override
    public String toData() {
        return checkDone()
                ? "E//1//" + getDescription() + "//" + this.datetime.getDate() + "//" + this.datetime.getTime()
                : "E//0//" + getDescription() + "//" + this.datetime.getDate() + "//" + this.datetime.getTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.datetime + ")";
    }
}
