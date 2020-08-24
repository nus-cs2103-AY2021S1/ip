package task;

public class Event extends Task {
    private final DukeDateTime dateTime;

    public Event(String content, DukeDateTime dateTime) {
        super(content);
        this.dateTime = dateTime;
    }

    public Event(String content, DukeDateTime dateTime, boolean isDone) {
        super(content, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
