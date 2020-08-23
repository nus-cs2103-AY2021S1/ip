public class Event extends Task {
    String eventTime;
    public Event(String description, boolean isDone) {
        super(description.split(" /at ")[0], isDone);
        this.eventTime = description.split(" /at ")[1];
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.eventTime + ")";
    }
}
