public class Event extends Task {
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public Event(String name, boolean done, String at) {
        super(name, done);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public Event setDone(boolean b) {
        return new Event(this.getName(), true, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
