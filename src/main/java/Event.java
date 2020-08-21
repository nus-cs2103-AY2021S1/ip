public class Event extends Task {
    private static final String identifier = "E";

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", Event.identifier, super.toString(), this.at);
    }

    @Override
    public String serialise() {
        return String.format("%s | %s | %s", Event.identifier, super.serialise(), this.at);
    }
}
