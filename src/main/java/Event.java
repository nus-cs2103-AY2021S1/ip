public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), at);
    }
}
