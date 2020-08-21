package duke;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String showTask() {
        return String.format("[%s]%s (at: %s)", this.getType(), super.showTask(), this.at);
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.at;
    }
}
