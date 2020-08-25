package duke.task;

public class Event extends Task {
    protected final String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toSaveFormat() {
        return "event " + super.toSaveFormat() + " /at " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
