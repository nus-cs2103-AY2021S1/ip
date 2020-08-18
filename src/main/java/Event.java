public class Event extends Task {
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at);
    }
}
