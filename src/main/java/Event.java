public class Event extends Task {
    private String eventTime;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
