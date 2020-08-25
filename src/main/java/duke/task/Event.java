package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a event item that inherits from Task.
 */
public class Event extends Task {

    /** Date of when the event is occurring. */
    protected Date date;
    /** Boolean that represents if the date contains a time. */
    protected boolean isTime;

    /**
     * Creates an event with a default not done.
     * @param description Description of event.
     * @param date Date of event.
     * @param isTime Indicates if date has a time.
     */
    public Event (String description, Date date, boolean isTime) {
        super(description);
        this.date = date;
        this.isTime = isTime;
    }

    /**
     * Creates an event.
     * @param description Description of event.
     * @param date Date of event.
     * @param isTime Indicates if date has a time.
     * @param isDone Indicates if event is done.
     */
    public Event (String description, Date date, boolean isTime, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.isTime = isTime;
    }


    /**
     * {@inheritDoc}
     * @return Event as a String.
     */
    @Override
    public String toString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "[E]" + super.toString() + " (at: " + new SimpleDateFormat(s).format(date) + ")";
    }

    /**
     * {@inheritDoc}
     * @return Event as a String.
     */
    @Override
    public String toStoredTextString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "E | " + super.toStoredTextString() + " | " + new SimpleDateFormat(s).format(date) + " | " + (isTime ? "1" : "0");
    }
}