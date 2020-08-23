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
