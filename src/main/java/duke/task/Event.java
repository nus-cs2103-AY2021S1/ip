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
     * @param description description of the Event
     * @param isDone whether the Event has been done
     * @param start start Date of the Event
     * @param end ending Date of the Event
     */
    public Event(String description, boolean isDone, Date start, Date end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an Event which has not been completed.
     *
     * @param description description of the Event
     * @param start start Date of the Event
     * @param end ending Date of the Event
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
     * @return the start date
     */
    public Date getStart() {
        return start;
    }

    /**
     * Returns the end date of this Event.
     *
     * @return the end ate
     */
    public Date getEnd() {
        return end;
    }
}
