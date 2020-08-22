public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    @Override
    public String toStore() {
        String div = " | ";
        return "E" + div + (isDone ? "1" : "0") + div + description + div + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
