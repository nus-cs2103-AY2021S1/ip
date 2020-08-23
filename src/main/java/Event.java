public class Event extends Task {
    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String done, String description, String at) {
        super(description);
        this.at = at;
        this.isDone = (done == "1");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String splitToString() {
        return "\n" + "E" + "/" + this.ifDone() + "/" + this.description + "/" + this.at;
    }
}