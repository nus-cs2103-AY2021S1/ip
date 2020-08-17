public class Event extends Task {
    private String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
