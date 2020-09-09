package duke.command;

import java.util.Date;

import duke.task.Event;

public class EventDateEdit implements Edit<Event> {
    private Date start;
    private Date end;

    public EventDateEdit(Date newStart, Date newEnd) {
        start = newStart;
        end = newEnd;
    }

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
