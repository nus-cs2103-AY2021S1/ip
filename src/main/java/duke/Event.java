package duke;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String showTask() {
        return String.format("[E]%s (at: %s)", super.showTask(), this.at);
    }

}
