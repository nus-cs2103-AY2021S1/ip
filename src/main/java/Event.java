public class Event extends Task {

    protected DukeDateTime at;

    public Event(String description, DukeDateTime at) {
        super(description);
        this.at = at;
    }

    public DukeDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")";
    }
}
