package task;

public class Event extends Task {
    public Event(boolean completed, String content, String time) {
        super(completed, content, time);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        String time = getTime();
        return String.format("[E][%s] %s (at: %s)", mark, content, time);
    }
}