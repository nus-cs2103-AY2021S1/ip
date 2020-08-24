public class Event extends Task {
    protected String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    Event(String description, String at, boolean done) {
        super(description, done);
        this.at = at;
    }
    @Override
    public String inputStyle() {
        return "event " + super.inputStyle() + " /at" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
