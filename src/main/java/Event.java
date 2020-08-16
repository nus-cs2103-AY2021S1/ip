public class Event extends Task {
    private final String time;
    Event (String description, String time) {
        super(description);
        this.time = time;
    }

    Event (String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public Event markDone() {
        return new Event(getDescription(), true, this.time);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(at: " + time + ")";
    }
}
