public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "E";
    }
    
    public Event(String uniqueId, boolean isDone, String description, String at) {
        super (uniqueId, isDone, description);
        this.at = at;
        this.taskType = "E";
    }
    
    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}