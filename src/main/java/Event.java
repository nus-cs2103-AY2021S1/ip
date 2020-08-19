public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), at);
    }
}
