public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
