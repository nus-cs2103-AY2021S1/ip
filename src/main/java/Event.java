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

    public String toCommand() {
        return done
                ? "done event " + text + " /at " + time
                : "event " + text + " /at " + time;
    }
}
