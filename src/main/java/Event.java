public class Event extends Task {
    private static final String STRING_FORMAT = "[E][%s] %s (at: %s)";

    protected String eventTime;

    public Event(String description, boolean isDone, String eventTime) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format(Event.STRING_FORMAT, getStatusIcon(), description, eventTime);
    }
}
