package seedu.bob.data.task;

import seedu.bob.data.dateandtime.DateAndTime;

import seedu.bob.exceptions.BobInvalidDateAndTimeException;

/**
 * Represents a deadline with inherited functionalities from Task.
 */
public class Deadline extends Task {
    private final DateAndTime dateAndTime;

    /**
     * Creates an undone deadline.
     *
     * @param description Description of the deadline.
     * @param date Date of the deadline.
     * @param time Time of the event.
     * @throws BobInvalidDateAndTimeException If date and time parsed is not valid.
     */
    public Deadline(String description, String date, String time)
            throws BobInvalidDateAndTimeException{
        super(description);
        this.dateAndTime = new DateAndTime(date, time);
    }

    private Deadline (String description, DateAndTime dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Marks deadline as done.
     *
     * @return A done deadline.
     */
    @Override
    public Deadline markDone() {
        return new Deadline(getDescription(), this.dateAndTime, true);
    }

    /**
     * Convert to string value of deadline to be stored as data.
     *
     * @return String to be stored in hard disk.
     */
    @Override
    public String convertToStringData() {
        return checkIsDone()
                ? "D/1/" + getDescription() + "/" + this.dateAndTime.getDate() + "/" + this.dateAndTime.getTime()
                : "D/0/" + getDescription() + "/" + this.dateAndTime.getDate() + "/" + this.dateAndTime.getTime();
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " +  this.dateAndTime + ")";
    }
}
