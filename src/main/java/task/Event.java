package task;

public class Event extends Task {
    private final String time;

    public Event(boolean completed, String content, String time) {
        super(completed, content);
        this.time = time;
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        return String.format("[E][%s] %s (at: %s)", mark, content, time);
    }
}