package main.java.emily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents an Event task which has a timestamp at a given time
 * and description
 */
public class Event extends Task {

    protected LocalDate at;
    protected String timeStamp;

    /**
     * Subclass of Task with a timestamp
     * @param description
     * @param timeStamp
     */
    public Event(String description, String timeStamp) {
        super(description);
        this.timeStamp = timeStamp;
        this.at = LocalDate.parse(timeStamp);
        this.type = 'E';
    }

    /**
     * Getter method to retrieve timestamp
     * @return String of the date in the form yyy-mm-dd
     */
    public String getAt() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
