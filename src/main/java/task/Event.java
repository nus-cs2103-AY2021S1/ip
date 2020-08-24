package task;

public class Event extends Task {
    private final String dateTime;

    public Event(String content, String dateTime) {
        super(content);
        this.dateTime = dateTime;
    }

    public Event(String content, String dateTime, boolean isDone) {
        super(content, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toDataFileFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, content, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
