public class Event extends Task {
    String timeAt;

    // TODO: 17/8/20 make a toString 
    public Event(String desc, String timeAt) {
        super(desc);
        this.timeAt = timeAt;
    }

    @Override
    public String toString() {
        String sign = done ? "✓" : "✗";
        return "[E][" + sign + "] " + description + " (at:" + timeAt + ")";
    }
}