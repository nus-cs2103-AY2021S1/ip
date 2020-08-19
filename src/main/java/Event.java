public class Event extends Task {
    String description;

    Event(String task, String description) {
        super(task);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.description + ")";
    }
}
