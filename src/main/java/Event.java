public class Event extends Task {
    String description;

    Event(String task, String description) {
        super(task);
        this.description = description;
    }

    Event(String task, String description, boolean done) {
        super(task, done);
        this.description = description;
    }
    
    @Override
    public String toDataString() {
        return "E // " + (done ? "1": "0") + " // " + task + " // " + description;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.description + ")";
    }
}
