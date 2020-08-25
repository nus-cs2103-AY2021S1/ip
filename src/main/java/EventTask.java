/**
 * <p>The EventTask class defines the behavior of an event task that has a
 * specific date and time when the event happens.</p>
 */
public class EventTask extends Task {
    private DateAndTime eventTime;

    public EventTask(String taskName, boolean isDone, DateAndTime eventTime) {
        super(taskName, isDone);
        this.eventTime = eventTime;
    }

    public DateAndTime getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + taskDescription + " (at: " + eventTime + ")";
    }
}
