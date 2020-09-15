package duke.task;

import java.time.LocalDate;

/**
 * Represents a Event Task. A <code>Event</code> object contains a description,
 * keeps track of whether it has been completed as well as the time.
 */
public class Event extends TimedTask {

    /**
     * Constructor for Event.
     */
    public Event(String description, LocalDate eventTime) {
        super(description, eventTime);
        super.type = Task.Type.EVENT;
    }

    /**
     * Constructor for Event with additional isDone parameter.
     */
    public Event(String description, LocalDate eventTime, boolean isDone) {
        super(description, eventTime);
        super.type = Task.Type.EVENT;
        super.isDone = isDone;
    }

    public String getTypeIcon() {
        String icon = "[E]";
        return icon;
    }

    public void snooze() {
        super.date = super.date.plusDays(1);
    }
}
