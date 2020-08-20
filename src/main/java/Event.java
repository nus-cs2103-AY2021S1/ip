public class Event extends Task {
    private static final String STRING_FORMAT = "[E][%s] %s (at: %s)";

    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format(Event.STRING_FORMAT, getStatusIcon(), description, eventTime);
    }
}
