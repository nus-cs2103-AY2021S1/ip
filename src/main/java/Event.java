public class Event extends Task {

   protected String at;

    public Event(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + " (at: " + at + ")";
    }
}