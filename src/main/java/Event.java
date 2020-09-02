/**
 * Represents a Event Task. A <code>Event</code> object contains a description,
 * keeps track of whether it has been completed as well as the time.
 */
public class Event extends TimedTask {

    public Event(String description, String eventTime) {
        super(description, eventTime);
        super.type = Task.Type.EVENT;
    }

    public Event(String description, String eventTime, boolean isDone) {
        super(description, eventTime);
        super.type = Task.Type.EVENT;
        super.isDone = isDone;
    }
}