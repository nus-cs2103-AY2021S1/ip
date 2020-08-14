public class Event extends Task {
    private final String event;
    Event(String task, String event) {
        super(task);
        this.event = event;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.event + ")";
    }
}
