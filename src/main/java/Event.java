public class Event extends Task {
    private String at;

    public Event(String isCompleted, String taskName, String by) {
        super(isCompleted, taskName);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
