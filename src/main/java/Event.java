public class Event extends Task{
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String taskRow() {
        return "[E]" + super.taskRow() + "(at: " + eventTime + ")";
    }
}
