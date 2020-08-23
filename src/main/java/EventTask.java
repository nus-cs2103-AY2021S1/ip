public class EventTask extends Task {
    private String eventTime;

    public EventTask(String description, boolean isDone, String eventTime) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventTime + ")";
    }
}
