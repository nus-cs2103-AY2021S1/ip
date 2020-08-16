public class Event extends Task {
    private String time;

    public Event(String s, String time) {
        super(s);
        this.time = time;
    }

    @Override
    public String toString() {
        return done
                ? "[E][✓] " + text + " (at: " + time + ")"
                : "[E][✗] " + text + " (at: " + time + ")";
    }
}
