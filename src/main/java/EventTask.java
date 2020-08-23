public class EventTask extends Task {
    private DateAndTime eventTime;

    public EventTask(String taskName, DateAndTime eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + taskDescription + " (at: " + eventTime + ")";
    }
}
