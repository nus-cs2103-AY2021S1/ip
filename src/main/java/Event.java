public class Event extends Task {
    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    
    public String getAtWhen() {
        return this.at;
    }

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.at = time;
    }
    
    @Override
    public String toString() {
            return String.format("[E]" + super.toString() + " (at: %s)", at);
    }
}
