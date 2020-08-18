public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    String[] parts = description.split("/at ");
    String[] subparts = parts[0].split("event");

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + subparts[1] + " (at: " + at + ")";
    }
}