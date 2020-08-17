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
    public Event markTaskAsDone() {
        return new Event(this.content, this.dateTime, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
