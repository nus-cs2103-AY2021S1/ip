package duke.task;

public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (at: %s)", at);
    }

    @Override
    protected String taskTypeString() {
        return "E";
    }
}
