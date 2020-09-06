package luke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(TaskType.EVENT, description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toDataString() {
        return String.format("E|%s|%s", super.toDataString(), this.getAt());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
