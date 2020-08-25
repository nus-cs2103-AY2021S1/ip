/**
 * Represents an event as a task with a date and/or time.
 */
public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String getData() {
        return "E / " + super.getData() + " / " + this.getAt();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAt() + ")";
    }
}
