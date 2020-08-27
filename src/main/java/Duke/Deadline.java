package Duke;

import Duke.Exception.*;

/**
 * Represents a Deadline task
 */
public class Deadline extends Task {

    private final DateAndTime dateTime;

    /**
     * Initialise a deadline object
     * @param description  Description of deadline
     * @param date         Date of event
     * @param time         Time of event
     * @throws InvalidDateTimeException
     */
    public Deadline(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.dateTime = new DateAndTime(date, time);
    }

    /**
     * Convert Deadline object to a string representation.
     * @return  String value to be stored in file
     */
    @Override
    public String toData() {
        return checkIsDone()
                ? "D//1//" + getDescription() + "//" + this.dateTime.getDate() + "//" + this.dateTime.getTime()
                : "D//0//" + getDescription() + "//" + this.dateTime.getDate() + "//" + this.dateTime.getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }
}
