public class Event extends Task {

    String timestamp;

    public Event(String raw) {
        super(raw.split("/at")[0].trim(), "E");
        this.timestamp = raw.split("/at")[1].trim();
    }

    private Event(String description, boolean isDone, String timestamp) {
        super(description, "E", isDone);
        this.timestamp = timestamp;
    }

    @Override
    public Event markDone() {
        return new Event(this.description, true, this.timestamp);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.timestamp + ")";
    }
}
