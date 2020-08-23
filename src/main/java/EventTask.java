public class EventTask extends Task {
    private String eventTime;

    public EventTask(String taskName, boolean isDone, String eventTime) {
        super(taskName, isDone);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + taskDescription + " (at:" + eventTime + ")";
    }
}
