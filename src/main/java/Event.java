public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toText() {
        String str = super.toText("E");
        str += "| " + this.at;
        return str;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
