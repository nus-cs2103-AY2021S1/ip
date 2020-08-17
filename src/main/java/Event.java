public class Event extends Task {
    private final String time;

    Event(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        return String.format("[E][%s] %s (at: %s)", mark, content, time);
    }
}