public class Event extends Task {
    protected String eventTime;

    Event(String desc) {
        super(desc.split(" /at ", 2)[0]);
        this.eventTime = desc.split(" /at ", 2)[1];
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + this.description + " (at: " + this.eventTime + ")";
    }
}
