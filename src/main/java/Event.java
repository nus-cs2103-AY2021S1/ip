public class Event extends Task {
    protected String at;

    public Event(String details, String at) {
        super(details);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
