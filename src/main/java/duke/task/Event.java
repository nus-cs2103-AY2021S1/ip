package duke.task;

public class Event extends Task {
    private String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = sanitizeString(at);
    }

    public Event(String description, String at) {
        this(description, false, at);
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (at: %s)", at);
    }

    @Override
    protected String taskTypeString() {
        return "E";
    }

    public String getAt() {
        return at;
    }
}
