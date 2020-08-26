public class Event extends Task {
    protected String at;

    public Event(String name, boolean isComplete, String at) {
        super(name, isComplete, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String getDetails() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at);
    }
}
