public class Event extends Task {
    private String eventTime;

    Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String status = String.format("[E][%s] ", (super.done ? "✓" : "✗"));
        String time = String.format(" (at: %s)", eventTime);
        return status + this.getName() + time;
    }
}
