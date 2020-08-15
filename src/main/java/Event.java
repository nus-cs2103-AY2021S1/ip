public class Event extends Task {
    String datetime;

    public Event(String content, String datetime) {
        super(content);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}
