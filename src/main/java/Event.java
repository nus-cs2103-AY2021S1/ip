public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description, "[E]");
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}