package fei.task;

public class Event extends Task {

    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String savedFormat() {
        return "E " + super.savedFormat() + String.format(" | %s", this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
