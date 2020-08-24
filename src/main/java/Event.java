public class Event extends Task {
    protected String duration;

    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}