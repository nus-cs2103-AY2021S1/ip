package duke.edit;

import java.util.Date;

import duke.task.Event;

/**
 * An Edit which changes the start and/or end date of an Event. An EditingException is thrown if an
 * invalid date is passed, ie. the start date will be after the end date.
 */
public class EventDateEdit implements Edit<Event> {
    private Date start;
    private Date end;

    /**
     * Creates a new EventDateEdit which will change the start and end date of an Event.
     *
     * @param newStart New start Date of the Event.
     * @param newEnd New end Date of the Event.
     */
    public EventDateEdit(Date newStart, Date newEnd) {
        start = newStart;
        end = newEnd;
    }

    /**
     * Creates a new EventDateEdit which will change either the start date or end date of an Event.
     *
     * @param newDate New start or end Date of the Event.
     * @param type Type of date.
     */
    public EventDateEdit(Date newDate, EventDateType type) {
        if (type.equals(EventDateType.START)) {
            start = newDate;
            end = null;
        } else {
            start = null;
            end = newDate;
        }
    }

    @Override
    public void apply(Event event) throws EditingException {
        String invalidDateMessage = "Start date cannot be after end date!";
        if (start != null && end != null) {
            if (start.after(end)) {
                throw new EditingException(invalidDateMessage);
            }
            event.setStart(start);
            event.setEnd(end);
        } else if (start != null) {
            if (start.after(event.getEnd())) {
                throw new EditingException(invalidDateMessage);
            }
            event.setStart(start);
        } else if (end != null) {
            if (event.getStart().after(end)) {
                throw new EditingException(invalidDateMessage);
            }
            event.setEnd(end);
        }
    }
}
