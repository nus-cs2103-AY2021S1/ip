package task;

public class Event extends Task{
    private final String time;

    private static final String EVENT= "[D]";
    public static final String EVENT_BREAK = "/at";

    Event(String description, boolean completed, String time) {
        super(description, completed);
        this.time = time;
    }

    public static Event createEvent(String description, String time) {
        return new Event(description, false, time);
    }

    @Override
    public String toString() {
        String atTime = String.format("(at: %s)", this.time);
        return EVENT + toStringSuffix() + " " + atTime;
    }
}
