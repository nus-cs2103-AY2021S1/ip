package duke.task;

public class Event extends Task {
    private final String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}