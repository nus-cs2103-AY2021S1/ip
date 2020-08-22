public class Event extends Task {

    private TimePoint when;

    public Event(String name, String when) {
        super(name);
        this.when = TimePoint.of(when);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.when.toString() + ")";
    }
}
