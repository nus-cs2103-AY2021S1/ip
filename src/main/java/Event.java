public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String summarize() {
        return String.format("E | %s | %s", super.summarize(), at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + at + ")";
    }
}

