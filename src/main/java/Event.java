public class Event extends Task {
    protected String at;

    public Event(String name, String at, boolean done) {
        super(name, done);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String writeString() {
        return "E # " + super.writeString() + " # " + at;
    }
}
