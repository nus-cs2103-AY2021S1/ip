package seedu.bob.data.task;

import seedu.bob.data.dateandtime.DateAndTime;
import seedu.bob.exceptions.BobInvalidDateAndTimeException;

/**
 * Represents a event with inherited functionalities from Task.
 */
public class Event extends Task {
    private final DateAndTime dateAndTime;

    /**
     * Creates an undone event.
     *
     * @param description Description of event.
     * @param date  Date of event.
     * @param time Time of event.
     * @throws BobInvalidDateAndTimeException If date and time parsed is not valid.
     */
    public Event(String description, String date, String time) throws BobInvalidDateAndTimeException{
        super(description);
        this.dateAndTime = new DateAndTime(date, time);
    }

    private Event (String description, DateAndTime dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Marks event as done.
     *
     * @return A done event.
     */
    @Override
    public Event markDone() {
        return new Event(getDescription(), this.dateAndTime, true);
    }

    /**
     * Convert to string value of event to be stored as data.
     *
     * @return String to be stored in hard disk.
     */
    @Override
    public String convertToStringData() {
        return checkIsDone()
                ? "E/1/" + getDescription() + "/" + this.dateAndTime.getDate() + "/" + this.dateAndTime.getTime()
                : "E/0/" + getDescription() + "/" + this.dateAndTime.getDate() + "/" + this.dateAndTime.getTime();
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (at: " + this.dateAndTime + ")";
    }
}
