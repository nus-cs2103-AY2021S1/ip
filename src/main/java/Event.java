package ip.src.main.java;

public class Event extends Task {
    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }

    @Override
    public String toSave() {
        return "E | " + getDoneInteger() + " | " + description + " | " + this.eventTime;
    }
}
