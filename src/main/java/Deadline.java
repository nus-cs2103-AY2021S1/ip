import java.util.Date;

/**
 * Represents a deadline with inherited functionalities from Task.
 * @author Lim Zi Yang
 */
public class Deadline extends Task {
    private final DateAndTime dateAndTime;

    /**
     * Creates an undone deadline.
     * @param description Description of the deadline.
     * @param date Date of the deadline.
     * @param time Time of the event.
     */
    Deadline (String description, String date, String time)
            throws BobInvalidDateAndTimeException{
        super(description);
        this.dateAndTime = new DateAndTime(date, time);
    }

    /**
     * Creates a deadline.
     * @param description Description of the deadline.
     * @param dateAndTime DateAndTime of deadline.
     * @param isDone Whether the deadline is done.
     */
    private Deadline (String description, DateAndTime dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Marks deadline as done.
     * @return A done deadline.
     */
    @Override
    public Deadline markDone() {
        return new Deadline(getDescription(), this.dateAndTime, true);
    }

    /**
     * Overridden toString method.
     * @return String value of deadline.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " +  this.dateAndTime + ")";
    }
}
