public class Event extends Task {
    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        super.type = Task.Type.EVENT;
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.description + this.getEventTime();
    }

    protected String getEventTime() {
        return " (at: " + this.eventTime + ")";
    }
}