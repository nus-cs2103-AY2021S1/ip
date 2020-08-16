public class EventTask extends Task {
    private String eventTime;

    public EventTask(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatusIcon() + "] " + taskName + " (at:" + eventTime + ")";
    }
}
