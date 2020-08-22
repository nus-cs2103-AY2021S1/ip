public class Event extends Task {
    protected String at;

    public Event(String description, String at, boolean done) {
        super(description, done);
        this.at = at;
    }

    public String getTiming() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}