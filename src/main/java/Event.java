public class Event extends Task {
    protected String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()+ String.format(" (at: %s)", time);
    }
}
