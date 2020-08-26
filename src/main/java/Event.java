public class Event extends Task {
    protected String at;
    
    static {
        format = "event <event description> /at <event location>";
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getParsedTask() {
        return "event " + this.description + " /at " + this.at + "\n"
                + this.done + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

}
