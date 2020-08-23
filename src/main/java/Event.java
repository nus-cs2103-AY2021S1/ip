public class Event extends Task {
    private String eventTime;

    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description + " (at: " + eventTime + ")";
    }

    @Override
    public String getTime() {
        return eventTime;
    }
}
