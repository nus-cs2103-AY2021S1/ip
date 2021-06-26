package duke.task;

import java.util.Date;

/**
 * This class represents an Event which will take place during a specific period of time.
 */
public class Event extends Task {
    private Date start;
    private Date end;

    /**
     * Creates an Event.
     *
     * @param description Description of the Event.
     * @param isDone Whether the Event has been done.
     * @param start Start Date of the Event.
     * @param end Ending Date of the Event.
     */
    public Event(String description, boolean isDone, Date start, Date end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an Event which has not been completed.
     *
     * @param description Description of the Event.
     * @param start Start Date of the Event.
     * @param end Ending Date of the Event.
     */
    public Event(String description, Date start, Date end) {
        this(description, false, start, end);
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (at: %s)", DateHelper.formatDateRange(start, end));
    }

    @Override
    protected String taskTypeString() {
        return "E";
    }

    /**
     * Returns the start date of this Event.
     *
     * @return Start date.
     */
    public Date getStart() {
        return start;
    }

    /**
     * Returns the end date of this Event.
     *
     * @return End date.
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Changes the start date of this Event.
     *
     * @param date New start date.
     */
    public void setStart(Date date) {
        this.start = date;
        onChange();
    }

    /**
     * Changes the end date of this Event.
     *
     * @param date New end date.
     */
    public void setEnd(Date date) {
        this.end = end;
        onChange();
    }
}
