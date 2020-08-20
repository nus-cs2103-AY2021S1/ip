public class Event extends Task {
    protected String at;
    
    static {
        format = "event <event description> /at <event location>";
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

}
