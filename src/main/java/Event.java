public class Event extends Task{
    String eventTime;
    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String taskRow() {
        return "[E]" + " " + getStatusIcon() + " " + this +
                "(at: " + eventTime + ")";
    }
}
