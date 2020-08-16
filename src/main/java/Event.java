public class Event extends Task {
    String timeAt;
    public Event(String s) {
        super(s.substring(0, s.lastIndexOf('/')));
        this.timeAt = s.substring(s.lastIndexOf('/') + 3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + timeAt + ")";
    }
}
