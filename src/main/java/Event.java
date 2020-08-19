public class Event extends Task {
    String eventTime;

    Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[EVENT]" + super.toString() + " | by: " + eventTime;
    }
}
